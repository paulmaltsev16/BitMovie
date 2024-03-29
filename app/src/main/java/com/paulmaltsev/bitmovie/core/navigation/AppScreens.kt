package com.paulmaltsev.bitmovie.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.paulmaltsev.bitmovie.R

sealed class AppScreens(
    val route: String,
    val icon: ImageVector? = null,
    val titleId: Int? = null
) {
    data object Favorites : AppScreens("favorites_screen", Icons.Default.Favorite, R.string.favorites)
    data object Menu : AppScreens("menu_screen", Icons.Default.Menu, R.string.menu)
    data object Home : AppScreens("home_screen", Icons.Default.Home, R.string.home)
    data object TmdbLegalContent : AppScreens("tmdb_legal_content_screen")
    data object TrailerPreview : AppScreens("trailer_preview_screen")
    data object MovieDetails : AppScreens("profile_screen")
    data object Category : AppScreens("category_screen")
}