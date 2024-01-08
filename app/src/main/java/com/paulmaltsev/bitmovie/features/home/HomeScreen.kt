package com.paulmaltsev.bitmovie.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.ui.theme.BitMovieTheme
import com.paulmaltsev.bitmovie.features.home.components.MovieListSection
import com.paulmaltsev.bitmovie.features.home.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val moviesUpcoming = viewModel.moviesUpcoming.collectAsStateWithLifecycle(arrayListOf())
    val moviesTopRated = viewModel.moviesTopRated.collectAsStateWithLifecycle(arrayListOf())
    val moviesNowPlaying = viewModel.moviesNowPlaying.collectAsStateWithLifecycle(arrayListOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = R.dimen.padding_big))
    ) {
        Image(
            painterResource(id = R.drawable.logo_app),
            stringResource(R.string.content_description_app_logo),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        MovieListSection(
            movies = moviesUpcoming.value,
            titleId = R.string.upcoming,
            navController = navController
        ) {
            viewModel.loadNextUpcomingMovies()
        }

        MovieListSection(
            movies = moviesTopRated.value,
            titleId = R.string.top_rated,
            navController = navController
        ) {
            viewModel.loadNextTopRatedMovies()
        }

        MovieListSection(
            movies = moviesNowPlaying.value,
            titleId = R.string.now_playing,
            navController = navController
        ) {
            viewModel.loadNextNowPlayingMovies()
        }
    }
}

@Preview
@Composable
fun BitItemPreview() {
    BitMovieTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}