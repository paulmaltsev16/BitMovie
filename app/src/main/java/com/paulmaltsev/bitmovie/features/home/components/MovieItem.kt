package com.paulmaltsev.bitmovie.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.paulmaltsev.bitmovie.BuildConfig
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.core.ui.theme.BitMovieTheme
import com.paulmaltsev.bitmovie.core.utils.VoidCallback

@Composable
fun MovieItem(
    movie: MovieModel,
    modifier: Modifier,
    onClick: VoidCallback
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(IntrinsicSize.Max)
        ) {
            AsyncImage(
                model = BuildConfig.TMDB_IMAGE_BASE_URL + movie.posterPath,
                contentDescription = stringResource(id = R.string.content_description_movie_remote_image),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
            )

            Text(
                text = movie.title ?: "-",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
                    .appPadding()
                    .zIndex(2f)
                    .align(Alignment.BottomStart),
            )
        }
    }
}

@Preview
@Composable
fun BeerItemPreview() {
    BitMovieTheme {
        MovieItem(
            movie = MovieModel(
                title = "Movie title",
                originalTitle = "Movie original title",
                releaseDate = "07-01-2024",
                posterPath = "https://media.istockphoto.com/id/825882974/photo/between-day-and-night.jpg?s=2048x2048&w=is&k=20&c=xHhs07OL7-l36jqHNg8nEpAxBBjinMmu8sr-FA4MOfs="
            ), modifier = Modifier.fillMaxSize()
        ) {}
    }
}