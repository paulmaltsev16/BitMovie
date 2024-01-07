package com.paulmaltsev.bitmovie.features.movieDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.ui.views.AppSpacer
import com.paulmaltsev.bitmovie.features.movieDetails.viewModel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(movieId: String?) {
    val viewModel = viewModel<MovieDetailsViewModel>()
    viewModel.getMovieDetailsById(movieId)
    val movie = viewModel.movie.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .appPadding()
    ) {

        AsyncImage(
            model = movie?.posterPath,
            contentDescription = stringResource(id = R.string.content_description_movie_remote_image),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f),
        )

        AppSpacer()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle favorite click */ }) {
                Icon(
                    imageVector = viewModel.getFavoriteIcon(),
                    contentDescription = stringResource(id = R.string.content_description_favorite_icon),
                    tint = Color.Black
                )
            }
            IconButton(onClick = { /* Handle play trailer click */ }) {
                Icon(
                    imageVector = Icons.Default.PlayCircle,
                    contentDescription = stringResource(id = R.string.content_description_play_icon),
                    tint = Color.Black
                )
            }
        }

        AppSpacer()

        Text(
            text = movie?.title ?: "-",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        AppSpacer()

        Text(
            text = "Original Title: ${movie?.originalTitle ?: "-"}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )

        AppSpacer(dimensionResource(id = R.dimen.padding_small))

        Text(
            text = "Release Date: ${movie?.releaseDate ?: "-"}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )

        AppSpacer()

        Text(
            text = movie?.overview ?: "-",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}

