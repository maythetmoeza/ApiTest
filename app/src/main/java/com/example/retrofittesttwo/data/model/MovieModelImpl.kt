package com.example.retrofittesttwo.data.model

import android.util.Pair
import com.example.retrofittesttwo.data.Cast
import com.example.retrofittesttwo.data.Crew
import com.example.retrofittesttwo.data.Movie
import com.example.retrofittesttwo.network.dataagents.MovieDataAgent
import com.example.retrofittesttwo.network.dataagents.RetrofitDataAgentImpl
import com.example.retrofittesttwo.data.Genre
import com.example.retrofittesttwo.data.PersonDetail
import com.example.retrofittesttwo.network.response.MovieDetailResponse

object MovieModelImpl : MovieModel {

    val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl
    override fun getNowPlayingMovies(
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieDetailResponse) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieDataAgent.getMovieDetail(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getCredit(
        movieId: String,
        onSuccess: (Pair<List<Cast>, List<Crew>>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieDataAgent.getCredit(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getGenreList(onSuccess: (List<Genre>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getGenre(
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getPersonalDetail(
        personId: String,
        onSuccess: (PersonDetail) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieDataAgent.getPersonDetail(
            personId = personId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getMovieByGenre(
        genreId: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieDataAgent.getMoviesByGenre(
            genreId = genreId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }


}

