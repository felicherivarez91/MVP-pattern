package com.example.zattootest.dagger.component

import com.example.zattootest.MyApp
import com.example.zattootest.presenter.ZattooPresenter
import com.example.zattootest.dagger.module.UserActivitiesModule
import com.example.zattootest.presenter.MovieItemsRetrofit
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserActivitiesModule::class])
interface ApplicationComponent {

    val movieItemsRetrofit : MovieItemsRetrofit

    val presenter : ZattooPresenter

    fun injectApplication(myApplication: MyApp)
}
