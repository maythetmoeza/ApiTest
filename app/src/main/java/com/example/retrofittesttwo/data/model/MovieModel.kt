package com.example.retrofittesttwo.data.model

import android.util.Pair
import com.example.retrofittesttwo.data.Cast
import com.example.retrofittesttwo.data.Crew
import com.example.retrofittesttwo.data.Genre
import com.example.retrofittesttwo.data.Movie
import com.example.retrofittesttwo.data.PersonDetail
import com.example.retrofittesttwo.network.response.MovieDetailResponse
import com.example.retrofittesttwo.network.response.MovieListResponse

interface MovieModel {

    fun getNowPlayingMovies(
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetail(
        movieId : String,
        onSuccess: (MovieDetailResponse) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCredit(
        movieId: String,
        onSuccess: (Pair<List<Cast>,List<Crew>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGenreList(
        onSuccess : (List<Genre>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPersonalDetail(
        personId : String,
        onSuccess: (PersonDetail) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieByGenre(
        genreId: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (String) -> Unit
    )
}