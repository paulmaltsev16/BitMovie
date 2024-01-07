package com.paulmaltsev.bitmovie.core.navigation

sealed class AppScreens(var route: String) {
    object Home : AppScreens("home_screen")
    object MovieDetails : AppScreens("profile_screen")
    object Favorites : AppScreens("favorites_screen")
    object TmdbLegalContent : AppScreens("tmdb_legal_content_screen")
}