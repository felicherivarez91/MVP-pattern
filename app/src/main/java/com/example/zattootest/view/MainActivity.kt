package com.example.zattootest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zattootest.MyApp
import com.example.zattootest.R
import com.example.zattootest.ZattooInterface
import com.example.zattootest.dagger.component.ApplicationComponent
import com.example.zattootest.dagger.component.DaggerMainActivityComponent
import com.example.zattootest.model.ArrayOffers
import com.example.zattootest.model.MovieDataDetails
import com.example.zattootest.presenter.ZattooPresenter
import com.example.zattootest.presenter.MovieItemsRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

//@author Dmitry Tkachuk
class MainActivity : AppCompatActivity() , ZattooInterface.View {

    private lateinit var appcomponent: ApplicationComponent

    @Inject lateinit var zattoopresenter: ZattooPresenter
    @Inject lateinit var movieoffersretrofit : MovieItemsRetrofit


    //Here we instantiate a dagger and attach a mainactivity to view instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appcomponent = MyApp.instance[this].getApplicationComponent()

        val activityComponent = DaggerMainActivityComponent.builder().
            applicationComponent(appcomponent)
            .build()
        activityComponent.injectMainActivity(this)
        zattoopresenter.attach(this)
    }

    //Set a RecyclerView adapter and load data from a server
    override fun initView() {
        movie_items.layoutManager = LinearLayoutManager(this)
        movie_items.adapter = ItemRecyclerViewAdapter()
        zattoopresenter.loaddata(movieoffersretrofit)
    }

    //Update a view with fresh data retreived from the server.
    //List contains pair of two results related to movie data and movie offers
    override fun updateViewData(list: List<Pair<MovieDataDetails, ArrayOffers>>?) {
        (movie_items.adapter as ItemRecyclerViewAdapter).setMovies(list)
        this.runOnUiThread {
                             (movie_items.adapter as ItemRecyclerViewAdapter).notifyDataSetChanged()
                           }
    }

}
