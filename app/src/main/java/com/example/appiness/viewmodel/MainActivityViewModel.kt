package com.example.appiness.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.appiness.model.Content
import com.example.appiness.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

abstract class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
     lateinit var repository : Repository

    abstract fun getList(searchTxt : String , from :Boolean) :  Observable<Content>

}