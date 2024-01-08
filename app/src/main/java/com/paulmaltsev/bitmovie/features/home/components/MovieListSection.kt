package com.paulmaltsev.bitmovie.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.core.navigation.AppScreens
import com.paulmaltsev.bitmovie.core.ui.views.MovieItem
import com.paulmaltsev.bitmovie.core.utils.VoidCallback

@Composable
fun MovieListSection(
    movies: ArrayList<MovieModel>,
    titleId: Int,
    navController: NavController,
    onSeeMoreClicked: VoidCallback
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.padding_standard),
                end = dimensionResource(id = R.dimen.padding_standard),
                top = dimensionResource(id = R.dimen.padding_big),
            )
    ) {
        MovieCollectionTitle(stringResource(id = titleId))

        Text(
            text = "see more",
            color = colorResource(id = R.color.main),
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
                .clickable(onClick = onSeeMoreClicked)
        )
    }

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
                    .width(170.dp)
                    .height(250.dp)
                    .fillMaxSize()
                    .height(IntrinsicSize.Max)
            ) {
                navController.navigate(AppScreens.MovieDetails.route + "/" + movie.id)
            }
        }
    }
}

@Preview
@Composable
fun BeerItemPreview() {
    val navController = rememberNavController()
    MovieListSection(
        movies = arrayListOf(
            MovieModel(
                title = "Hello there"
            )
        ),
        titleId = R.string.app_name,
        navController = navController,
        onSeeMoreClicked = {}
    )
}