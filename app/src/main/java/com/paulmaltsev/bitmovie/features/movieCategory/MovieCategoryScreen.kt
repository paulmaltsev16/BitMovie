package com.paulmaltsev.bitmovie.features.movieCategory

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.paulmaltsev.bitmovie.core.data.constants.MoviesCollectionType
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.navigation.AppScreens
import com.paulmaltsev.bitmovie.core.ui.views.AppPlaceholder
import com.paulmaltsev.bitmovie.core.ui.views.AppTopBar
import com.paulmaltsev.bitmovie.core.ui.views.MovieItem
import com.paulmaltsev.bitmovie.features.movieCategory.viewModel.MovieCategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCategoryScreen(
    movieCollectionTypeIndex: Int,
    navController: NavController,
) {
    val movieCollectionType = MoviesCollectionType.values()[movieCollectionTypeIndex]
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val viewModel = viewModel<MovieCategoryViewModel>()
    val movies = viewModel.movies.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = AppTopBar(
            title = stringResource(movieCollectionType.screenTitleId),
            scrollBehavior = scrollBehavior
        ) {
            navController.popBackStack()
        }
    ) { paddingValues ->

        if (movies.isEmpty()) {
            AppPlaceholder()
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier
                .padding(paddingValues)
                .appPadding()
                .fillMaxSize(),
        ) {
            items(
                count = movies.size
            ) {
                val movie = movies[it]
                MovieItem(
                    movie = movie,
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    navController.navigate(AppScreens.MovieDetails.route + "/" + movie.id)
                }
            }

            item {
                LaunchedEffect(true) {
                    viewModel.downloadMovies(movieCollectionType)
                }
            }
        }
    }
}