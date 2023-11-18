@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.retrofittesttwo.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.retrofittesttwo.IMAGE_URL
import com.example.retrofittesttwo.R
import com.example.retrofittesttwo.data.Cast
import com.example.retrofittesttwo.data.Crew
import com.example.retrofittesttwo.data.model.MovieModel
import com.example.retrofittesttwo.data.model.MovieModelImpl
import com.example.retrofittesttwo.network.response.MovieDetailResponse


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun detailScreen(navController: NavController, movieId: String) {

    val mMovieModel: MovieModel = MovieModelImpl

bodyScreen(movieId = movieId, mMovieDetailModel = mMovieModel, navController = navController)

}

@Composable
fun bodyScreen(mMovieDetailModel : MovieModel = MovieModelImpl, movieId: String, navController: NavController){

    var movieDetail: MovieDetailResponse by remember {
        mutableStateOf(MovieDetailResponse())
    }

    mMovieDetailModel.getMovieDetail(movieId = movieId,
        onSuccess = {
            movieDetail = it

        },
        onFailure = {
            println(it)
        })

    aboutMovie(movieDetail = movieDetail, movieId = movieId, navController = navController)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun aboutMovie(movieDetail: MovieDetailResponse, movieId: String, navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(movieDetail.title.toString(), color = Color.White) },
            Modifier.background(Color(0xff0f9d58))
        )

    }) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val lazyListState = rememberLazyListState()
            var scrolledY = 0f
            var previousOffset = 0
            LazyColumn(
                Modifier
                    .fillMaxSize(),
                lazyListState,
            ) {

                item {

                    Box {
                        AsyncImage(
                            model = "$IMAGE_URL" + movieDetail.backdrop_path,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .graphicsLayer {
                                    scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                                    translationY = scrolledY * 0.5f
                                    previousOffset = lazyListState.firstVisibleItemScrollOffset
                                }
                                .height(400.dp)
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)

                        )
                        Icon(painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                            contentDescription ="",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(80.dp)
                                .padding(20.dp)
                        )

                    }



                    Spacer(modifier = Modifier.padding(bottom = 5.dp))
                }
                item {
                    Row {
                        Text(text = "Popularity", Modifier.padding(start = 10.dp))
                        Icon(painter = painterResource(id = R.drawable.baseline_full_star_24),
                            contentDescription ="", Modifier.padding(horizontal = 10.dp) )
                        Text(text = movieDetail.popularity.toString())
                    }
                }

                item {
                    Row {
                        Text(text = "Release Date", Modifier.padding(start = 10.dp))
                        Text(text = movieDetail.release_date.toString(), Modifier.padding(start = 10.dp))
                    }
                }
                item {

                        Text(text = "Actor List", Modifier.padding(10.dp))

                }
                item {
                    creditOnlyActor(movieId = movieId, navController)
                }
                item {

                    Text(text = "Creator List", Modifier.padding(10.dp))

                }

                item{
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    creditOnlyCreator(movieId = movieId, navController)
                }

                item {
                   Text(text = "Overview", Modifier.padding(10.dp), fontWeight = FontWeight.Bold)
                }
                item {
                    Text(text = movieDetail.overview.toString(), Modifier.padding(start = 10.dp))
                }





            }



        }


    }

}

//@Preview
//@Composable
//fun show(){
//    Box {
//        Image(
//            painterResource(id = R.drawable.poster),
//            contentDescription = "",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(400.dp)
//                .fillMaxWidth()
//                .padding(bottom = 10.dp)
//
//        )
//        Icon(painter = painterResource(id = R.drawable.baseline_favorite_border_24),
//            contentDescription ="",
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .size(55.dp)
//        )
//    }
//}


@Composable
fun creditOnlyActor(movieId: String, navController: NavController){

    val mMovieModel: MovieModel = MovieModelImpl

    var castList: List<Cast> by remember {
        mutableStateOf(listOf())
    }

    var crewList: List<Crew> by remember {
        mutableStateOf(listOf())
    }

    mMovieModel.getCredit(
        movieId = movieId,
        onSuccess ={
            castList = it.first
            crewList = it.second
        } ,
        onFailure = {

        },
    )

    showActor(castList = castList, navController)

}


@Composable
fun showActor(castList: List<Cast>, navController: NavController){


    LazyRow(modifier = Modifier.fillMaxSize()){
        items(castList.size){

            AsyncImage(model = "$IMAGE_URL"+castList[it].profile_path ,
                contentDescription ="" ,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(70.dp)
                    .border(2.dp, Color.Gray, CircleShape)
                    .clickable {
                        navController.navigate("personDetail/${castList[it].id}")
                    }
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun creditOnlyCreator(movieId: String, navController: NavController){

    val mMovieModel: MovieModel = MovieModelImpl

    var castList: List<Cast> by remember {
        mutableStateOf(listOf())
    }

    var crewList: List<Crew> by remember {
        mutableStateOf(listOf())
    }

    mMovieModel.getCredit(
        movieId = movieId,
        onSuccess ={
            castList = it.first
            crewList = it.second
        } ,
        onFailure = {

        },
    )

showCreator(crewList = crewList, navController)

}


@Composable
fun showCreator(crewList: List<Crew>, navController: NavController){

    LazyRow(modifier = Modifier.fillMaxSize()){
        items(crewList.size){

            AsyncImage(model = "$IMAGE_URL"+crewList[it].profile_path ,
                contentDescription ="" ,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(70.dp)
                    .border(2.dp, Color.Gray, CircleShape)
                    .clickable {
                        navController.navigate("personDetail/${crewList[it].id}")
                    })
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}




