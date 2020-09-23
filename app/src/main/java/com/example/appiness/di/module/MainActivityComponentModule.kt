package com.example.appiness.di.module

import com.example.appiness.db.AppDatabase
import com.example.appiness.repository.Repository
import com.example.appiness.repository.RepositoryImpl
import com.example.appiness.webservice.WebService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainActivityComponentModule {

    @Provides
    fun getRepository(retrofit: Retrofit?,appDatabase : AppDatabase) : Repository {
        return RepositoryImpl(retrofit?.create(WebService::class.java),appDatabase)
    }

}