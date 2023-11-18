@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.retrofittesttwo.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.retrofittesttwo.IMAGE_URL
import com.example.retrofittesttwo.data.Movie
import com.example.retrofittesttwo.data.model.MovieModel
import com.example.retrofittesttwo.data.model.MovieModelImpl
import com.example.retrofittesttwo.data.model.SearchMovieViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow

@Composable
fun movieListByGenre(navController: NavController, genreId : String ){

    val mMovieModel : MovieModel = MovieModelImpl
//    var movieList : List<Movie> by remember{ mutableStateOf(listOf())}

    var searchModel  = viewModel<SearchMovieViewModel>()
    var allMovie : List<Movie> by remember {
        mutableStateOf(listOf())
    }

    mMovieModel.getMovieByGenre(
        genreId = genreId,
        onSuccess = {
//            movieList = it
                    searchModel.allMovie = it

        },
        onFailure = {

        }
    )

    val searchText by searchModel.searchText.collectAsState()
    val movies by searchModel.movies.collectAsState()
    val isSearching by searchModel.isSearching.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = searchModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Serach") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)

            ) {
                items(movies) {
                    Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                        AsyncImage(
                            model = "$IMAGE_URL" + it.poster_path,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                navController.navigate("detail/${it.id}")
                            }
                        )


                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                        Row {
                            Text(text = "Title", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                            Text(
                                text = it.title.toString(),
                                modifier = Modifier.padding(bottom = 10.dp)
                            )

                        }
                    }
//
                }
            }
        }


//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        modifier = Modifier
//            .padding(start = 10.dp, top = 10.dp)
//
//    ) {
//        items(movieList){
//            Column(modifier = Modifier.padding(horizontal = 5.dp)) {
//                AsyncImage(
//                    model = "$IMAGE_URL"+it.poster_path,
//                    contentDescription = "",
//                    modifier = Modifier.clickable {
//                        navController.navigate("detail/${it.id}")
//                    }
//                )
//
//
//                Spacer(modifier = Modifier.padding(vertical = 10.dp))
//                Row {
//                    Text(text = "Title", fontWeight = FontWeight.Bold)
//                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))
//                    Text(text = it.title.toString(), modifier = Modifier.padding(bottom = 10.dp))
//
//                }
//            }
////
//        }}

    }
    
}

