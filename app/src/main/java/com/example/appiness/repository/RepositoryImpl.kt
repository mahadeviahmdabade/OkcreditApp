package com.example.appiness.repository

import android.util.Log
import com.example.appiness.db.AppDatabase
import com.example.appiness.model.*
import com.example.appiness.webservice.WebService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RepositoryImpl(var webService: WebService?, var appDatabase: AppDatabase) : Repository {

    override fun getSearchListFromDb(searchTxt: String): Observable<Content> {

        return Observable.create<Content> {
            appDatabase.getMovieInfoDao().getMoviesInfo(searchTxt).let { list ->
                if (list != null)
                    it.onNext(DataModelEncapsulator().apply {
                        data = list
                    })
                else
                    it.onNext(ErrorModel())
            }

        }.flatMap {
            if (it.getContentType() == ContentType.ERRORMODEL) {
                getList("")
            } else {
                Observable.just(it)
            }
        }

    }

    override fun getList(searchTxt: String): Observable<Content> {
        return Single.create<Content> {
            webService?.getResult(searchTxt)?.enqueue(object : Callback<Apiresponse> {
                override fun onFailure(call: Call<Apiresponse>, t: Throwable) {
                    Log.d("response", "" + t)
                }

                override fun onResponse(call: Call<Apiresponse>, response: Response<Apiresponse>) {
                    it.onSuccess(DataModelEncapsulator().apply {
                        data = response.body()?.items as List<ItemsItem>?
                    })
                }
            })

        }.doOnSuccess {
            if (it.getContentType() == ContentType.DATAMODEL) {
                doAsync {
                    appDatabase.getMovieInfoDao().insert((it as DataModelEncapsulator).data!!)
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()

            }
        }.toObservable()

    }

    private fun doAsync(task: () -> Unit): Observable<Content> {
        return Observable.create<Content> {
            task()
        }

    }


}