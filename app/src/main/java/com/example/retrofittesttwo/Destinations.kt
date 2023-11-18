package com.example.retrofittesttwo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations (val route: String,
                           val title: String? = null,
                           val icon: ImageVector? = null
) {
    object HomeScreen : Destinations(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Favourite : Destinations(
        route = "favourite_screen",
        title = "Favorite",
        icon = Icons.Outlined.Favorite
    )

    object Category : Destinations(
        route = "category_screen",
        title = "Genre",
        icon = Icons.Outlined.List
    )

}
