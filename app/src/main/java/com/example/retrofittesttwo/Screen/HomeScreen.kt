@file:OptIn(ExperimentalFoundationApi::class)

package com.example.retrofittesttwo.Screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.retrofittesttwo.IMAGE_URL
import com.example.retrofittesttwo.R
import com.example.retrofittesttwo.data.Movie
import com.example.retrofittesttwo.data.model.MovieModel
import com.example.retrofittesttwo.data.model.MovieModelImpl
import kotlin.math.absoluteValue


@Composable
fun movie(navController: NavHostController) {

    val mMovieModel: MovieModel = MovieModelImpl
    var movieList: List<Movie> by remember {
        mutableStateOf(listOf())
    }


    mMovieModel.getNowPlayingMovies(
        onSuccess = {
            movieList = it
        },
        onFailure = {
            Log.d("Fail", "no response")
        }
    )

    Column {
        Row(Modifier.weight(2.5f)) {
            horizontalPager(movieList = movieList, navController)

        }
        Row(Modifier.weight(1.5f)) {
            movieListByCategory(movieList = movieList, navController = navController)
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)

@Composable
fun horizontalPager(movieList: List<Movie>, navController: NavController) {

    var pageCount = 0

    if (movieList.size > 10) {
        pageCount = 10
    } else {
        pageCount = movieList.size
    }

    val pagerState = rememberPagerState(pageCount = { pageCount })

    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(pageCount),
    )


    Column {
        Row (modifier = Modifier.weight(3f)) {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                beyondBoundsPageCount = 10,
                flingBehavior = fling,
                contentPadding = PaddingValues(horizontal = 75.dp),
                pageSpacing = 10.dp,
                modifier = Modifier
                    .padding(top = 10.dp),

            ) { page ->

                Card(
                    modifier = Modifier
                        .graphicsLayer
                        {
                            // Calculate the absolute offset for the current page from the
                            // scroll position. We use the absolute value which allows us to mirror
                            // any effects for both directions
                            val pageOffset = (
                                    (pagerState.currentPage - page) + pagerState
                                        .currentPageOffsetFraction
                                    ).absoluteValue


                            // We animate the alpha, between 50% and 100%
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also {
                                scaleY = it
                            }

                        }
                ) {
                    AsyncImage(
                        model = "$IMAGE_URL" + movieList[page].poster_path,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navController.navigate("detail/${movieList[page].id}")
                        }
                    )

                }


            }
        }


        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(10.dp)
                .weight(.4f),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}


@Composable
fun movieListByCategory(movieList: List<Movie>, navController: NavController) {

    LazyRow {
        items(movieList) {
            listScreen(
                movieTitle = it.title.toString(),
                poster = it.poster_path.toString(),
                rating = it.vote_average.toString(),
                movieID = it.id.toString(),
                navController = navController
            )
        }
    }

}


@Composable
fun listScreen(movieTitle: String, poster: String, rating: String, navController: NavController, movieID : String) {

    Column {
        Row (
            Modifier
                .weight(3f)
                .padding(start = 15.dp)){
            AsyncImage(
                model = "$IMAGE_URL" + poster,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        navController.navigate("detail/$movieID")
                    }
            )
        }

        Row (
            Modifier
                .weight(1f)
                .padding(top = 5.dp)
        ) {
            showTitle(title = movieTitle, lineLimit = 12)
        }

        Row(modifier = Modifier
            .padding(start = 10.dp, top = 5.dp)
            .weight(1f)) {

            Image(
                painter = painterResource(id = R.drawable.baseline_full_star_24),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .padding(horizontal = 5.dp)

            )
            Text(text = rating.toString())
        }


    }
}


@Composable
fun showTitle(title: String, lineLimit: Int){

    if (title.length > lineLimit) {

        Column {
            Text(
                text = title.substring(0, lineLimit),
//                color = Color.White,
                modifier = Modifier.padding(start = 15.dp),
                fontWeight = FontWeight.Bold

            )
            Text(
                text = title.substring(lineLimit),
//                color = Color.White,
                modifier = Modifier.padding(start = 15.dp),
                fontWeight = FontWeight.Bold
            )
        }

    } else {

        Text(
            text = title,
//            color = Color.White,
            modifier = Modifier.padding(start = 15.dp),
            fontWeight = FontWeight.Bold
        )
    }
}





