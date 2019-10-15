package com.example.zattootest.model

import android.os.Bundle
import com.example.zattootest.ZattooInterface

//For storing data in model
class ZattooModel : ZattooInterface.Model {

    private var bundle : Bundle = Bundle()
    private var movieData : MovieData? = null
    private var movieOffers : MovieOffers? = null

    override fun savedata(any: Any?) {
        if (any is MovieData){
            movieData = any
            bundle.putSerializable("MovieData", movieData)
        }
        else if (any is MovieOffers){
            movieOffers = any
            bundle.putSerializable("MovieOffer", movieOffers)
        }
    }
}