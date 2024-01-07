package com.paulmaltsev.bitmovie.features.favorites

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.navigation.AppScreens
import com.paulmaltsev.bitmovie.core.ui.views.AppTopBar
import com.paulmaltsev.bitmovie.core.ui.views.MovieItem
import com.paulmaltsev.bitmovie.features.favorites.viewModel.FavoritesViewModel

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = viewModel()
) {
    val movies = viewModel.movies.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = AppTopBar(
            title = stringResource(id = R.string.favorites)
        ) {
            navController.popBackStack()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
        ) {
            items(movies.size) {
                val movie = movies[it]
                MovieItem(
                    movie = movie,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .appPadding()
                ) {
                    navController.navigate(AppScreens.MovieDetails.route + "/" + movie.id)
                }
            }
        }
    }
}