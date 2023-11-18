package com.example.retrofittesttwo.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("adult")
    val adult : Boolean?,

    @SerializedName("backdrop_path")
    val backdropPath : String?,

    @SerializedName("genre_ids")
    val genre_ids: List<Int?>?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("original_language")
    val original_language: String?,

    @SerializedName("original_title")
    val original_title: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val poster_path: String?,

    @SerializedName("release_date")
    val release_date: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val vote_average: Double?,

    @SerializedName("vote_count")
    val vote_count: Int?,

//    @SerializedName("belongs_to_collection")
//    val belongsToCollection: Any?,
//    @SerializedName("budget")
//    val budget: Int?,
//    @SerializedName("genres")
//    val genres: List<Genre?>?,
//    @SerializedName("homepage")
//    val homepage: String?,
//
//    @SerializedName("imdb_id")
//    val imdbId: String?,
//    @SerializedName("original_language")
//    val originalLanguage: String?,
//    @SerializedName("original_title")
//    val originalTitle: String?,
//    @SerializedName("poster_path")
//    val posterPath: String?,
//    @SerializedName("production_companies")
//    val productionCompanies: List<ProductionCompany?>?,
//    @SerializedName("production_countries")
//    val productionCountries: List<ProductionCountry?>?,
//    @SerializedName("release_date")
//    val releaseDate: String?,
//    @SerializedName("revenue")
//    val revenue: Int?,
//    @SerializedName("runtime")
//    val runtime: Int?,
//    @SerializedName("spoken_languages")
//    val spokenLanguages: List<SpokenLanguage?>?,
//    @SerializedName("status")
//    val status: String?,
//    @SerializedName("tagline")
//    val tagline: String?,


){
    fun doesMatchSearchQuery (query: String): Boolean{

         return original_title!!.contains(query, ignoreCase = true)

    }
}
