package com.paulmaltsev.bitmovie.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.navigation.AppScreens
import com.paulmaltsev.bitmovie.core.utils.VoidCallback

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painterResource(id = R.drawable.logo_app),
            stringResource(R.string.content_description_app_logo),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Button(onClick = { navController.navigate(AppScreens.MovieCategory.route) }) {
            Text(text = "Go to movie category")
        }
        Button(onClick = { navController.navigate(AppScreens.MovieDetails.route) }) {
            Text(text = "Go to movie details")
        }
        Button(onClick = { navController.navigate(AppScreens.Favorites.route) }) {
            Text(text = "Go to movie favorites")
        }
        Button(onClick = { navController.navigate(AppScreens.TmdbLegalContent.route) }) {
            Text(text = "Go to tmdb")
        }

        DataSourceLegend {
            navController.navigate(AppScreens.TmdbLegalContent.route)
        }
    }
}

@Composable
private fun DataSourceLegend(onClick: VoidCallback) {
    Text(
        stringResource(id = R.string.data_provided_by_tmdb),
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .appPadding()
            .clickable(onClick = onClick)
    )
}