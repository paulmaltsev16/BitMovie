package com.paulmaltsev.bitmovie.features.movieDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.paulmaltsev.bitmovie.BuildConfig
import com.paulmaltsev.bitmovie.core.navigation.AppScreens
import com.paulmaltsev.bitmovie.core.ui.views.AppIconButton
import com.paulmaltsev.bitmovie.core.ui.views.AppPlaceholder
import com.paulmaltsev.bitmovie.features.movieDetails.components.MovieDetailedImage
import com.paulmaltsev.bitmovie.features.movieDetails.components.MovieDetails
import com.paulmaltsev.bitmovie.features.movieDetails.viewModel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    movieId: String?,
    navController: NavController,
    viewModel: MovieDetailsViewModel = viewModel()
) {
    viewModel.getMovieDetailsById(movieId)
    val movie = viewModel.movie.collectAsStateWithLifecycle().value
    val isFavoriteMovie = viewModel.isFavoriteMovie.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            MovieDetailedImage(BuildConfig.TMDB_IMAGE_BASE_URL + movie?.posterPath)

            AppIconButton(Icons.AutoMirrored.Filled.ArrowBack) {
                navController.popBackStack()
            }
        }

        if (movie == null) {
            AppPlaceholder()
        } else {
            MovieDetails(
                movie = movie,
                isFavoriteMovie = isFavoriteMovie,
                onFavoriteIconClick = {
                    viewModel.updateFavoriteWithMovie()
                },
                onPlayIconClick = {
                    navController.navigate(AppScreens.TrailerPreview.route + "/" + movieId)
                }
            )
        }
    }
}