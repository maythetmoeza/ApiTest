package com.example.retrofittesttwo.network.response

import com.example.retrofittesttwo.data.Date
import com.example.retrofittesttwo.data.Movie
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("dates")
    val data : Date?,

    @SerializedName("page")
    val page : Int?,

    @SerializedName("results")
    val movies : List<Movie>?,

    @SerializedName("total_pages")
    val totalPage : Int?,

    @SerializedName("total_results")
    val totalResult : Int?
)
