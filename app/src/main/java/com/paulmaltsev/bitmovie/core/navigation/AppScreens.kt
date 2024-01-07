package com.paulmaltsev.bitmovie.core.navigation

sealed class AppScreens(var route: String) {
    object Home : AppScreens("home_screen")
}