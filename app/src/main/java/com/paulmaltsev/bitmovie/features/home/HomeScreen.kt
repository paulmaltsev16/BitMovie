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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
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
    val movies = viewModel.movies.collectAsStateWithLifecycle(arrayListOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(16.dp),
        ) {
            items(movies.value.size) {
                val movie = movies.value[it]
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


@Preview
@Composable
fun BeerItemPreview() {
    BitMovieTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}
