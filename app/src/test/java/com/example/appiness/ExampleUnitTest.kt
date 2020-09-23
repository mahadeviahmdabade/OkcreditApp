package com.example.appiness

import android.app.Application
import com.example.appiness.db.AppDatabase
import com.example.appiness.model.ContentType
import com.example.appiness.model.DataModel
import com.example.appiness.model.DataModelEncapsulator
import com.example.appiness.repository.RepositoryImpl
import com.example.appiness.viewmodel.MainActivityViewModelImpl
import com.example.appiness.webservice.WebService
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Mock
    lateinit var repositoryImpl  : RepositoryImpl


    @get:Rule
    var mockitoRule = MockitoJUnit.rule()



    val application = Mockito.mock(Application::class.java)


    @Mock
    lateinit var appDb: AppDatabase

    @Mock
    lateinit var webService: WebService







}
