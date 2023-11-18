package com.example.retrofittesttwo.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.retrofittesttwo.IMAGE_URL
import com.example.retrofittesttwo.R
import com.example.retrofittesttwo.data.PersonDetail
import com.example.retrofittesttwo.data.model.MovieModel
import com.example.retrofittesttwo.data.model.MovieModelImpl


@Composable
fun personDetail(navController: NavController, personId: String){

    val mMovieModel : MovieModel = MovieModelImpl
    var personDetail by remember{ mutableStateOf(PersonDetail())}

    mMovieModel.getPersonalDetail(
        personId = personId,
        onSuccess = {
            personDetail = it
        },
        onFailure = {

        }
    )

   Column {
       AsyncImage(
           model = "$IMAGE_URL"+personDetail.profile_path,
           contentDescription ="" ,
           contentScale = ContentScale.Crop,
           modifier = Modifier.size(width = 400.dp, height = 300.dp)
       )
//       Image(
//           painter = painterResource(id = R.drawable.actor),
//           contentDescription ="",
//           contentScale = ContentScale.Crop,
//           modifier = Modifier.size(width = 400.dp, height = 300.dp)
//       )
       Row (modifier = Modifier.padding(start = 20.dp, top = 20.dp)){
           Column (verticalArrangement = Arrangement.spacedBy(10.dp)){
               Text(text = "Name" )
               Text(text = "Popularity" )
               Text(text = "Birthday" )
               Text(text = "Place of Birth" )
           }
           Spacer(modifier = Modifier.padding(horizontal = 20.dp))
           Column (verticalArrangement = Arrangement.spacedBy(10.dp)) {
               Text(text = personDetail.name )
               Text(text = personDetail.popularity.toString() )
               Text(text = personDetail.birthday )
               Text(text = personDetail.place_of_birth )
           }

       }

       Text(text = "Biography",
           Modifier.padding(start = 20.dp, top = 20.dp),
           fontWeight = FontWeight.Bold
           )
       Text(text = personDetail.biography ?: "",
           modifier = Modifier.padding(start = 20.dp, top = 20.dp)
           )
   }

}