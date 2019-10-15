package com.example.zattootest

import com.example.zattootest.model.ArrayOffers
import com.example.zattootest.model.MovieDataDetails
import com.example.zattootest.presenter.MovieItemsRetrofit

/*
 * @author Dmitry Tkachuk
 */
interface ZattooInterface {

        /*
         * Intercommunication between MainActivity, presenter and model
         * Here the MainActivity and the view are same things
         */
        interface View {
            fun initView()
            fun updateViewData(list: List<Pair<MovieDataDetails, ArrayOffers>>?)
        }

        interface Presenter {
            fun attach(view : View)
            fun loaddata(movieItemsRetrofit: MovieItemsRetrofit)
        }

        interface Model {
            fun savedata(any : Any?)
        }
}