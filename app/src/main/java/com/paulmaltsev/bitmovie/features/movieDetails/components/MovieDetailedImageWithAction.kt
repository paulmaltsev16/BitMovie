package com.paulmaltsev.bitmovie.features.movieDetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.paulmaltsev.bitmovie.R

@Composable
fun MovieDetailedImage(stringUrl: String) {
    Box {
        AsyncImage(
            model = stringUrl,
            contentDescription = stringResource(id = R.string.content_description_movie_remote_image),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f),
        )
    }
}