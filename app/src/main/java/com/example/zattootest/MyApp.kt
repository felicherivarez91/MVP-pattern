package com.example.zattootest

import android.app.Activity
import android.app.Application
import com.example.zattootest.dagger.component.ApplicationComponent
import com.example.zattootest.dagger.component.DaggerApplicationComponent

class MyApp : Application(){

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    //Instantiate a Dagger application component
    fun setup() {
        component = DaggerApplicationComponent.builder().build()
        component.injectApplication(this)
    }

    //Here we return our instance in order
    // to link Application and MainActivity
    operator fun get(activity: Activity): MyApp {
        return activity.application as MyApp
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }
    companion object {
        lateinit var instance: MyApp private set
    }
}
