package com.paulmaltsev.bitmovie.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.paulmaltsev.bitmovie.core.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavController) {
    Column {
        Text(
            "Home screen",
            textAlign = TextAlign.Center
        )
        Button(onClick = { navController.navigate(AppScreens.MovieCategory.route) }) {
            Text(text = "Go to movie category")
        }
        Button(onClick = { navController.navigate(AppScreens.MovieDetails.route) }) {
            Text(text = "Go to movie details")
        }
        Button(onClick = { navController.navigate(AppScreens.TmdbLegalContent.route) }) {
            Text(text = "Go to tmdb")
        }
    }
}