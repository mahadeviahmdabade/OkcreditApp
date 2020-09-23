package com.example.appiness.webservice

import com.example.appiness.model.Apiresponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService{
    @GET("/search/users?")
    fun getResult(@Query("q") searchParameter : String) : Call<Apiresponse>

}