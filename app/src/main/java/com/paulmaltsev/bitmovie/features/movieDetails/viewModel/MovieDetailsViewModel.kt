package com.paulmaltsev.bitmovie.features.movieDetails.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.constants.Constants
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.features.favorites.repository.FavoriteRepository
import com.paulmaltsev.bitmovie.features.favorites.repository.FavoriteRepositoryImpl
import com.paulmaltsev.bitmovie.features.home.repository.MoviesRepository
import com.paulmaltsev.bitmovie.features.home.repository.MoviesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences = application.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, 0)
    private val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl(preferences)
    private val movieRepository: MoviesRepository = MoviesRepositoryImpl(RetrofitClient)

    private var _movie = MutableStateFlow<MovieModel?>(null)
    val movie get() = _movie.asStateFlow()

    private var _isFavoriteMovie = MutableStateFlow(false)
    val isFavoriteMovie get() = _isFavoriteMovie.asStateFlow()

    fun getMovieDetailsById(id: String?) = viewModelScope.launch(Dispatchers.IO) {
        val movie = movieRepository.getMovieDetailsById(id)
        _movie.emit(movie)
        updateIsFavoriteFlow()
    }

    fun updateFavoriteWithMovie() {
        movie.value?.let { movie ->
            favoriteRepository.updateFavorites(movie)
            updateIsFavoriteFlow()
        }
    }

    private fun updateIsFavoriteFlow() {
        viewModelScope.launch {
            val isFavoriteMovie = favoriteRepository.isFavoriteMovie(movieId = movie.value?.id)
            _isFavoriteMovie.emit(isFavoriteMovie)
        }
    }
}