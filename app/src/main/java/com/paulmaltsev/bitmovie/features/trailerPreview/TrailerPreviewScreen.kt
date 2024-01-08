package com.paulmaltsev.bitmovie.features.trailerPreview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun TrailerPreviewScreen(
    movieId: Int,
    navController: NavHostController
) {
    Text("Trailer preview, movie id- $movieId")
}