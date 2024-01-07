package com.paulmaltsev.bitmovie.features.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.constants.MoviesCollections
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.features.home.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var _moviesUpcoming = MutableStateFlow(arrayListOf<MovieModel>())
    val moviesUpcoming = _moviesUpcoming.asStateFlow()

    private var _moviesTopRated = MutableStateFlow(arrayListOf<MovieModel>())
    val moviesTopRated = _moviesTopRated.asStateFlow()

    private var _moviesNowPlaying = MutableStateFlow(arrayListOf<MovieModel>())
    val moviesNowPlaying = _moviesNowPlaying.asStateFlow()

    private val moviesRepository = MoviesRepository(RetrofitClient)

    init {
        downloadMovies()
    }

    private fun downloadMovies() = viewModelScope.launch(Dispatchers.IO) {
        val result = listOf(
            async {
                val upcoming = moviesRepository.downloadMovies(MoviesCollections.UPCOMING, 1)
                _moviesUpcoming.emit(upcoming)
            },
            async {
                val topRated = moviesRepository.downloadMovies(MoviesCollections.TOP_RATED, 1)
                _moviesTopRated.emit(topRated)
            },
            async {
                val nowPlaying = moviesRepository.downloadMovies(MoviesCollections.NOW_PLAYING, 1)
                _moviesNowPlaying.emit(nowPlaying)
            }
        )
        result.awaitAll()
    }
}