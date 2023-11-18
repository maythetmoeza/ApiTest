package com.example.retrofittesttwo.network.dataagents
import android.util.Pair
import com.example.retrofittesttwo.data.Cast
import com.example.retrofittesttwo.data.Crew
import com.example.retrofittesttwo.data.Genre
import com.example.retrofittesttwo.data.Movie
import com.example.retrofittesttwo.data.PersonDetail
import com.example.retrofittesttwo.network.response.MovieDetailResponse

interface MovieDataAgent {

    fun getNowPlayingMovies(
       onSuccess: (List<Movie>) -> Unit,
        onFailure : (String) -> Unit
    )

   fun getMovieDetail(
       movieId : String,
       onSuccess : (MovieDetailResponse) -> Unit,
       onFailure: (String) -> Unit
   )

   fun getCredit(
       movieId: String,
       onSuccess: (Pair<List<Cast>, List<Crew>>) -> Unit,
       onFailure: (String) -> Unit
   )

   fun getGenre(
       onSuccess: (List<Genre>) -> Unit,
       onFailure: (String) -> Unit
   )

   fun getPersonDetail(
       personId : String,
       onSuccess : (PersonDetail) -> Unit,
       onFailure: (String) -> Unit
   )

   fun getMoviesByGenre(
       genreId : String,
       onSuccess: (List<Movie>) -> Unit,
       onFailure: (String) -> Unit
   )
}