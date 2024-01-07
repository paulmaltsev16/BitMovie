package com.paulmaltsev.bitmovie.features.favorites.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.constants.Constants
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.features.favorites.repository.FavoriteRepository
import com.paulmaltsev.bitmovie.features.favorites.repository.FavoriteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences = application.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, 0)
    private val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl(preferences)
    private var _movies = MutableStateFlow(arrayListOf<MovieModel>())
    val movies get() = _movies.asStateFlow()

    fun getFavoriteMovies() = viewModelScope.launch(Dispatchers.IO) {
        val movies = favoriteRepository.getMoviesFromPreferences()
        _movies.emit(movies)
    }
}