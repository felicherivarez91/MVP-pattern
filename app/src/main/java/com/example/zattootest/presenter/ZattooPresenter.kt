package com.example.zattootest.presenter

import com.example.zattootest.ZattooInterface
import com.example.zattootest.model.ZattooModel
import com.example.zattootest.model.MovieData
import com.example.zattootest.model.MovieOffers
import com.example.zattootest.model.Constants.Companion.IMAGEBASE_URL
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

//@author Dmitry Tkachuk
class ZattooPresenter : ZattooInterface.Presenter {

    private lateinit var mview: ZattooInterface.View
    private var model: ZattooInterface.Model = ZattooModel()
    private var moviedata : MovieData? = null
    private var movieOffers : MovieOffers? = null

    //Attach the presenter to the injected view
    override fun attach(view: ZattooInterface.View) {
        this.mview = view
        mview.initView()
    }

    //Load data from the server
    override fun loaddata(movieItemsRetrofit: MovieItemsRetrofit) {
        val movieoffersobservable = movieItemsRetrofit.getMovieOffers()
        val moviedataobservable = movieItemsRetrofit.getMovieData()

        Observable.merge(moviedataobservable, movieoffersobservable)
                                                                    .subscribeOn(Schedulers.io())
                                                                    .subscribe {this.emitItems(it)}
    }

    private fun emitItems(it: Any?) {
        if (it is MovieData){
            moviedata = it
            model.savedata(it)
        }
        else if (it is MovieOffers){
            model.savedata(it)
            movieOffers = it
            var list = moviedata?.mmovie_data?.zip(movieOffers!!.mmovie_offers)
            IMAGEBASE_URL = movieOffers!!.mimage_base
            mview.updateViewData(list)
        }

    }

}