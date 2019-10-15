package com.example.zattootest.presenter

import com.example.zattootest.model.MovieData
import com.example.zattootest.model.MovieOffers
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieItemsRetrofit{

    @GET("/movie-offers/")
    fun getMovieOffers() : Observable<MovieOffers>

    @GET("/movie-data/")
    fun getMovieData() : Observable<MovieData>

}