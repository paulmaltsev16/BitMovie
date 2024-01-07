package com.paulmaltsev.bitmovie.features.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.constants.MoviesCollections
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.features.home.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var _movies = MutableStateFlow(arrayListOf<MovieModel>())
    val movies = _movies.asStateFlow()
    private val moviesRepository = MoviesRepository(RetrofitClient)

    init {
        downloadMovies()
    }

    private fun downloadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = moviesRepository.downloadMovies(MoviesCollections.UPCOMING, 1)
            _movies.emit(movies)
        }
    }
}