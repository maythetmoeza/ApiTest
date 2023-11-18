@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.retrofittesttwo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.retrofittesttwo.Screen.FavouriteScreen
import com.example.retrofittesttwo.Screen.GenreScreen
import com.example.retrofittesttwo.Screen.detailScreen
import com.example.retrofittesttwo.Screen.movie
import com.example.retrofittesttwo.Screen.movieListByGenre
import com.example.retrofittesttwo.Screen.personDetail
import com.example.retrofittesttwo.ui.theme.RetrofitTestTwoTheme


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitTestTwoTheme {

                val navController: NavHostController = rememberNavController()

                NavHost(navController = navController, startDestination = "main"){
                    composable("main"){
                        BottomNavBar(navController)
                    }
                    composable(
                        route = "detail/{movieId}",
                        arguments = listOf(
                            navArgument("movieId") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        detailScreen(
                            navController,
                            it.arguments?.getString("movieId") ?: "0"
                        )
                    }
                    composable(
                        route = "personDetail/{personId}",
                        arguments = listOf(
                            navArgument("personId") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        personDetail(
                            navController,
                            it.arguments?.getString("personId") ?: "0"
                        )
                    }

                    composable(
                        route = "movieByGenre/{genreId}",
                        arguments = listOf(
                            navArgument("genreId") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        movieListByGenre(
                           navController,
                            it.arguments?.getString("genreId") ?: "0"
                        )
                    }
                }


            }
        }
    }
}

@Composable
fun BottomNavBar(mainController: NavHostController){
    val buttonsVisible = remember { mutableStateOf(true) }
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                state = buttonsVisible,
                modifier = Modifier
            )
        }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavigationGraph(mainNavController = mainController, navController)

        }

    }
}


@Composable
fun NavigationGraph(mainNavController: NavHostController, bottomNavController: NavHostController) {


    NavHost(navController = bottomNavController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            movie(mainNavController)
        }
        composable(Destinations.Favourite.route) {
            FavouriteScreen()
        }
        composable(Destinations.Category.route) {
            GenreScreen(mainNavController)
        }

    }
}


@Composable
fun BottomBar(
    navController: NavHostController, state: MutableState<Boolean>, modifier: Modifier = Modifier,
) {
    val screens = listOf(
        Destinations.HomeScreen, Destinations.Favourite, Destinations.Category
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Color.Gray,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->

            NavigationBarItem(
                label = {
                    Text(text = screen.title!!)
                },
                icon = {
                    Icon(imageVector = screen.icon!!, contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.White, selectedTextColor = Color.Blue
                ),
            )
        }
    }

}






