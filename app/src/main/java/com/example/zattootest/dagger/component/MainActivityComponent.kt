package com.example.zattootest.dagger.component

import com.example.zattootest.view.MainActivity
import com.example.zattootest.dagger.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface MainActivityComponent {

    fun injectMainActivity(mainActivity: MainActivity)

}


