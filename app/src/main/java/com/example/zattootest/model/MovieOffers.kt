package com.example.zattootest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieOffers(@SerializedName("image_base") val mimage_base : String,
                       @SerializedName("movie_offers") val mmovie_offers :List<ArrayOffers>
                      ) : Serializable

    data class ArrayOffers (@SerializedName("price") val mpice : String,
                            @SerializedName("image") val mimage :String,
                            @SerializedName("available") val mavailable : Boolean,
                            @SerializedName("movie_id") val mmovie_id : Int
                           )
