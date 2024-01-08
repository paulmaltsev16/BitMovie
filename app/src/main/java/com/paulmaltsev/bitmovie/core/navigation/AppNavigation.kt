package com.paulmaltsev.bitmovie.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.paulmaltsev.bitmovie.features.favorites.FavoritesScreen
import com.paulmaltsev.bitmovie.features.home.HomeScreen
import com.paulmaltsev.bitmovie.features.legalContent.tmdb.TmdbScreen
import com.paulmaltsev.bitmovie.features.menu.MenuScreen
import com.paulmaltsev.bitmovie.features.movieCategory.MovieCategoryScreen
import com.paulmaltsev.bitmovie.features.movieDetails.MovieDetailsScreen

@Composable
fun AppNavigation(
    navController: NavHostController, modifier: Modifier
) {
    NavHost(
        navController = navController, startDestination = AppScreens.Home.route, modifier = modifier
    ) {
        composable(AppScreens.Home.route) {
            HomeScreen(navController)
        }
        composable(AppScreens.MovieDetails.route + "/" + "{movieId}") {
            val id = it.arguments?.getString("movieId")
            MovieDetailsScreen(id, navController)
        }
        composable(AppScreens.Favorites.route) {
            FavoritesScreen(navController)
        }
        composable(AppScreens.TmdbLegalContent.route) {
            TmdbScreen(navController)
        }
        composable(AppScreens.Menu.route) {
            MenuScreen(navController)
        }
        composable(AppScreens.Category.route + "/" + "{screenTitleId}") {
            // It can crash, but we want to know if something is wrong earlier as possible.
            val screenTitleId = it.arguments?.getString("screenTitleId")?.toInt()
            MovieCategoryScreen(screenTitleId!!, navController)
        }
    }
}


