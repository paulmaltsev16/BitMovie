package com.paulmaltsev.bitmovie.features.movieDetails.viewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.data.remote.api.MoviesApi
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {

    private var _movie = MutableStateFlow<MovieModel?>(null)
    val movie get() = _movie.asStateFlow()

    fun getMovieDetailsById(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            id ?: return@launch
            val api = RetrofitClient.instance.create(MoviesApi::class.java)
            val result = api.getMovieDetails(id)
            result.body()?.let { movie ->
                _movie.emit(movie)
            }
        }
    }

    fun getFavoriteIcon(): ImageVector {
        val isMovieInFavorite = true
        return if (isMovieInFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite
    }
}