package com.paulmaltsev.bitmovie.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.core.navigation.AppScreens
import com.paulmaltsev.bitmovie.core.ui.theme.BitMovieTheme
import com.paulmaltsev.bitmovie.core.ui.views.MovieItem
import com.paulmaltsev.bitmovie.core.utils.VoidCallback
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
        Box {
            Image(
                painterResource(id = R.drawable.logo_app),
                stringResource(R.string.content_description_app_logo),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )

            IconButton(
                onClick = { navController.navigate(AppScreens.Favorites.route) },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.content_description_favorite_icon)
                )
            }
        }

        DataSourceLegend {
            navController.navigate(AppScreens.TmdbLegalContent.route)
        }

        MovieCollectionName(stringResource(id = R.string.upcoming))
        MovieLazyList(moviesUpcoming.value, navController) {
            viewModel.loadNextUpcomingMovies()
        }

        MovieCollectionName(stringResource(id = R.string.top_rated))
        MovieLazyList(moviesTopRated.value, navController) {
            viewModel.loadNextTopRatedMovies()
        }

        MovieCollectionName(stringResource(id = R.string.now_playing))
        MovieLazyList(moviesNowPlaying.value, navController) {
            viewModel.loadNextNowPlayingMovies()
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

@Composable
private fun MovieCollectionName(name: String) {
    Text(
        text = name,
        color = colorResource(id = R.color.blue_main),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.padding_standard),
            end = dimensionResource(id = R.dimen.padding_standard),
            top = dimensionResource(id = R.dimen.padding_big),
        )
    )
}

@Composable
private fun MovieLazyList(
    movies: ArrayList<MovieModel>,
    navController: NavController,
    onReachEnd: VoidCallback
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(movies.size) {
            val movie = movies[it]
            MovieItem(
                movie = movie,
                modifier = Modifier
                    .width(250.dp)
                    .height(150.dp)
                    .fillMaxSize()
                    .height(IntrinsicSize.Max)
            ) {
                navController.navigate(AppScreens.MovieDetails.route + "/" + movie.id)
            }
        }
        item {
            LaunchedEffect(true) {
                onReachEnd()
            }
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
