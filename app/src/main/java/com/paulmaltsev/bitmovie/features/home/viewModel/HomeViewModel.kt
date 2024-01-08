package com.paulmaltsev.bitmovie.features.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.constants.MoviesCollectionType
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.core.repositories.MoviesRepository
import com.paulmaltsev.bitmovie.core.repositories.MoviesRepositoryImpl
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

    // Should be injected with dagger
    private val moviesRepository: MoviesRepository = MoviesRepositoryImpl(RetrofitClient)

    init {
        downloadMovies()
    }

    private fun downloadMovies() = viewModelScope.launch(Dispatchers.IO) {
        val result = listOf(
            async {
                val upcoming = moviesRepository.downloadMovies(
                    MoviesCollectionType.UPCOMING, 1
                )
                _moviesUpcoming.emit(upcoming)
            },
            async {
                val topRated = moviesRepository.downloadMovies(
                    MoviesCollectionType.TOP_RATED, 1
                )
                _moviesTopRated.emit(topRated)
            },
            async {
                val nowPlaying = moviesRepository.downloadMovies(
                    MoviesCollectionType.NOW_PLAYING, 1
                )
                _moviesNowPlaying.emit(nowPlaying)
            }
        )
        result.awaitAll()
    }
}