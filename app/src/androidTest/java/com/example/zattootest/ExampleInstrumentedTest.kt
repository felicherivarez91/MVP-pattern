package com.example.zattootest

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.zattootest.model.ZattooModel
import com.example.zattootest.presenter.ZattooPresenter
import com.example.zattootest.view.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var zattopresenter : ZattooPresenter
    private lateinit var mainactivity : MainActivity
    private lateinit var zattomodel : ZattooModel

    @Before
    fun testpresentercreation(){
        zattopresenter = ZattooPresenter()
        zattomodel = ZattooModel()
        mainactivity = activityRule.activity
        assertNotNull(zattopresenter)
        assertNotNull(mainactivity)
    }

    @Test
    fun tessavedata(){
        var any :  Any? = null
        zattomodel.savedata(any)
    }

    @Test
    fun testpresenter(){
        mainactivity.runOnUiThread{ zattopresenter.attach(mainactivity)}
        zattopresenter.loaddata(mainactivity.movieoffersretrofit)
        mainactivity.updateViewData(null)
    }

}
