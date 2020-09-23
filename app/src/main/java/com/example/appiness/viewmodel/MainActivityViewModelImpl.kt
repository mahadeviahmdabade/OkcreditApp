package com.example.appiness.viewmodel

import android.app.Application
import com.example.appiness.di.component.DaggerMainActivityComponent
import com.example.appiness.di.module.UtilityModule
import com.example.appiness.model.Content
import io.reactivex.Observable

class MainActivityViewModelImpl(application: Application) : MainActivityViewModel(application) {
    init {
        DaggerMainActivityComponent.builder().utilityModule(UtilityModule(application)).build().inject(this)
    }

    override fun getList(searchTxt : String , from :Boolean) : Observable<Content> {

        if (searchTxt.isNotEmpty() && !from) {
            return repository.getSearchListFromDb(searchTxt)
        }
        else
        {
            return  repository.getList(searchTxt)
        }

    }


}