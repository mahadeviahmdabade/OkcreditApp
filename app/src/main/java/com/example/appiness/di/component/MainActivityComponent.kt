package com.example.appiness.di.component

import com.example.appiness.di.module.MainActivityComponentModule
import com.example.appiness.di.module.UtilityModule
import com.example.appiness.viewmodel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MainActivityComponentModule::class,UtilityModule::class))
interface MainActivityComponent {

    fun inject(mainActivityViewModel: MainActivityViewModel)

}