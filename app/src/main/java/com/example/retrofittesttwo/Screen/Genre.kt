package com.example.retrofittesttwo.Screen

import android.graphics.Paint
import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.retrofittesttwo.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.retrofittesttwo.data.model.MovieModel
import com.example.retrofittesttwo.data.model.MovieModelImpl
import com.example.retrofittesttwo.data.Genre
import com.example.retrofittesttwo.ui.theme.Purple40


@Composable
fun GenreScreen(navController: NavController) {

    val mMovieModel: MovieModel = MovieModelImpl

    var genreList: List<Genre> by remember {
        mutableStateOf(listOf())
    }
    mMovieModel.getGenreList(
        onSuccess = {
            genreList = it
        },
        onFailure = {

        }
    )

    showGenre(genreList = genreList, navController)

}

@Composable
fun showGenre(genreList : List<Genre>, navController: NavController) {


    val gradientColors = listOf(White, Green, Cyan, Yellow)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 10.dp, top = 10.dp)
    ) {
            items(genreList){
                Box (
                    modifier = Modifier.padding(bottom = 10.dp, end = 10.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.genre),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navController.navigate("movieByGenre/${it.id}")
                        }
                        )
                    Text(
                        text = it.name.toString(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                        style = TextStyle(
                            brush = Brush.linearGradient(gradientColors)
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(10.dp),

                        )
                }
            }
    }

//    LazyColumn{
//        items(genreList.chunked(2)){rowItems ->
//            Row{
//                rowItems.forEach {
//                    Card {
//                        Text(text = it, modifier = Modifier.padding(20.dp))
//
//                    }
//                }
//                if (rowItems.size == 1) {
//                    Spacer(modifier = Modifier.weight(1f))
//                }
//            }
//        }
//    }
//   LazyColumn{
//       items(genreList.windowed(2,step = 2)){ rowItem ->
//            Row {
//                rowItem.forEach {
//                    Text(text = it)
//                }
//            }
//       }
//   }

}