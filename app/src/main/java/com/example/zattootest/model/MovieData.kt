package com.example.zattootest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieData(
                     @SerializedName("movie_data") val mmovie_data : List<MovieDataDetails>
                    ) : Serializable

data class MovieDataDetails(
                            @SerializedName("movie_id") val mmovie_id : Int,
                            @SerializedName("title") val mtitle : String,
                            @SerializedName("sub_title") val msub_title : String
                           )