package com.example.retrofittesttwo.network.dataagents

import android.util.Pair
import com.example.retrofittesttwo.BASE_URL
import com.example.retrofittesttwo.MovieApi
import com.example.retrofittesttwo.data.Cast
import com.example.retrofittesttwo.data.Credit
import com.example.retrofittesttwo.data.Crew
import com.example.retrofittesttwo.data.Genre
import com.example.retrofittesttwo.data.GenreList
import com.example.retrofittesttwo.data.Movie
import com.example.retrofittesttwo.data.PersonDetail
import com.example.retrofittesttwo.network.response.MovieDetailResponse
import com.example.retrofittesttwo.network.response.MovieListResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : MovieDataAgent {

    private var mTheMovieApi : MovieApi? = null

    init {
        val mOkhttpClient = OkHttpClient.Builder()
            .connectTimeout(15,TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieApi = retrofit.create(MovieApi::class.java)

    }

    override fun getNowPlayingMovies(
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mTheMovieApi?.getNowPlayingMovies()
            ?.enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>,
                ) {
                    if(response.isSuccessful){
                        val movieList = response.body()?.movies ?: listOf()
                        onSuccess(movieList)
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }


            })
    }

    override fun getMovieDetail(
        movieID: String,
        onSuccess: (MovieDetailResponse) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mTheMovieApi?.getMovieDetail(movieId = movieID)
            ?.enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>,
                ) {
                    if(response.isSuccessful){
                       response.body()?.let {
                           onSuccess(it)
                       }
                    }else{
                        println("error")
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    println(t.message)
                }

            })

    }

    override fun getCredit(
        movieId: String,
        onSuccess: (Pair<List<Cast>, List<Crew>>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mTheMovieApi?.getCredit(movieId = movieId)
            ?.enqueue(object : Callback<Credit> {
                override fun onResponse(call: Call<Credit>, response: Response<Credit>) {
                    if(response.isSuccessful){
                        val castList = response.body()?.cast ?: listOf()
                        val crewList = response.body()?.crew ?: listOf()
                      onSuccess(Pair(castList as List<Cast>?, crewList as List<Crew>?))
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<Credit>, t: Throwable) {
                   onFailure(t.message ?: "")
                }

            })
    }

    override fun getGenre(
        onSuccess: (List<Genre>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mTheMovieApi?.getGenre()
            ?.enqueue(object : Callback<GenreList> {
                override fun onResponse(
                    call: Call<GenreList>,
                    response: Response<GenreList>,
                ) {
                    if(response.isSuccessful){
                        val genreList = response.body()?.genres ?: listOf()
                        onSuccess(genreList)
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GenreList>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })

    }

    override fun getPersonDetail(
        personId: String,
        onSuccess: (PersonDetail) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mTheMovieApi?.getPersonDetail(personId = personId)
            ?.enqueue(object : Callback<PersonDetail> {
                override fun onResponse(
                    call: Call<PersonDetail>,
                    response: Response<PersonDetail>,
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            onSuccess(it)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<PersonDetail>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mTheMovieApi?.getMovieByGenre(genreId = genreId)
            ?.enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>,
                ) {
                    if(response.isSuccessful){
                        val movieList = response.body()?.movies ?: listOf()
                        onSuccess(movieList)
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

}

