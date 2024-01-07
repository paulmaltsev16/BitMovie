package com.paulmaltsev.bitmovie.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.paulmaltsev.bitmovie.features.favorites.FavoritesScreen
import com.paulmaltsev.bitmovie.features.home.HomeScreen
import com.paulmaltsev.bitmovie.features.legalContent.tmdb.TmdbScreen
import com.paulmaltsev.bitmovie.features.movieCategory.MovieCategoryScreen
import com.paulmaltsev.bitmovie.features.movieDetails.MovieDetailsScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.route
    ) {
        composable(AppScreens.Home.route) {
            HomeScreen(navController)
        }
        composable(AppScreens.MovieCategory.route) {
            MovieCategoryScreen()
        }
        composable(AppScreens.MovieDetails.route + "/" + "{movieId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("movieId")
            MovieDetailsScreen(id)
        }
        composable(AppScreens.Favorites.route) {
            FavoritesScreen()
        }
        composable(AppScreens.TmdbLegalContent.route) {
            TmdbScreen(navController)
        }
    }
}


