package com.example.retrofittesttwo
import com.example.retrofittesttwo.data.Credit
import com.example.retrofittesttwo.data.GenreList
import com.example.retrofittesttwo.data.PersonDetail
import com.example.retrofittesttwo.network.response.MovieDetailResponse
import com.example.retrofittesttwo.network.response.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET(NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(PARAM_PAGE) page : Int = 1

    ) : Call<MovieListResponse>


    @GET("$MOVIE_DETAIL/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId : String,
        @Query(PARAM_API_KEY) apiKey : String = API_KEY,

    ) : Call<MovieDetailResponse>

    @GET("$Credit/{movie_id}/credits")
    fun getCredit(
        @Path("movie_id") movieId : String,
        @Query(PARAM_API_KEY) apiKey : String = API_KEY,
    ) : Call<Credit>

    @GET("$Genre_List")
    fun getGenre(
        @Query(PARAM_API_KEY) apiKey : String = API_KEY,
    ) : Call<GenreList>

    @GET("$PERSON_DETAIL/{person_id}")
    fun getPersonDetail(
        @Path("person_id") personId : String,
        @Query(PARAM_API_KEY) apiKey : String = API_KEY
    ) : Call<PersonDetail>

    @GET("$MOVIE_BY_GENRE")
    fun getMovieByGenre(
        @Query(PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(PARAM_GENRE) genreId : String
    ) : Call<MovieListResponse>

}