package com.example.appiness.repository

import com.example.appiness.model.Content
import io.reactivex.Observable

interface Repository {

     fun getList(searchTxt: String) : Observable<Content>

     fun getSearchListFromDb(searchTxt: String) : Observable<Content>

}