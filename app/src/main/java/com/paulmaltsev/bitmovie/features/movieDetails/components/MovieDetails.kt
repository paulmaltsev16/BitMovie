package com.paulmaltsev.bitmovie.features.movieDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.core.ui.views.AppIconButton
import com.paulmaltsev.bitmovie.core.ui.views.AppSpacer
import com.paulmaltsev.bitmovie.core.utils.VoidCallback

@Composable
fun MovieDetails(
    movie: MovieModel,
    isFavoriteMovie: Boolean,
    onFavoriteIconClick: VoidCallback
) {
    Column(
        modifier = Modifier
            .appPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = if (isFavoriteMovie) Icons.Default.Favorite else Icons.Rounded.FavoriteBorder
            AppIconButton(
                imageVector = icon,
                onClick = onFavoriteIconClick
            )
        }

        AppSpacer()

        Text(
            text = movie.title ?: "-",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        AppSpacer()

        Text(
            text = stringResource(id = R.string.original_title, movie.originalTitle ?: "-"),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )

        AppSpacer(dimensionResource(id = R.dimen.padding_small))

        Text(
            text = stringResource(id = R.string.release_date, movie.releaseDate ?: "-"),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )

        AppSpacer(dimensionResource(id = R.dimen.padding_small))


        Text(
            text = stringResource(R.string.rating, movie.voteAverage ?: "-"),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )

        AppSpacer()

        Text(
            text = movie.overview ?: "-",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.large_text_color)
        )
    }
}