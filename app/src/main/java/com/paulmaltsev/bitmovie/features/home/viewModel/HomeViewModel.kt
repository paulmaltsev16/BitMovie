package com.paulmaltsev.bitmovie.features.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.constants.MoviesCollections
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.features.home.repository.MoviesRepository
import com.paulmaltsev.bitmovie.features.home.repository.MoviesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var upcomingCurrentPage = 1
    private var _moviesUpcoming = MutableStateFlow(arrayListOf<MovieModel>())
    val moviesUpcoming = _moviesUpcoming.asStateFlow()

    private var topRatedCurrentPage = 1
    private var _moviesTopRated = MutableStateFlow(arrayListOf<MovieModel>())
    val moviesTopRated = _moviesTopRated.asStateFlow()

    private var nowPlayingCurrentPage = 1
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
                    MoviesCollections.UPCOMING,
                    upcomingCurrentPage
                )
                _moviesUpcoming.emit(upcoming)
            },
            async {
                val topRated = moviesRepository.downloadMovies(
                    MoviesCollections.TOP_RATED,
                    1
                )
                _moviesTopRated.emit(topRated)
            },
            async {
                val nowPlaying = moviesRepository.downloadMovies(
                    MoviesCollections.NOW_PLAYING,
                    1
                )
                _moviesNowPlaying.emit(nowPlaying)
            }
        )
        result.awaitAll()
    }

    fun loadNextUpcomingMovies() = viewModelScope.launch {
        upcomingCurrentPage += 1
        val movies = moviesRepository.downloadMovies(
            MoviesCollections.UPCOMING, upcomingCurrentPage
        )
        val list = moviesUpcoming.value + movies
        _moviesUpcoming.emit(ArrayList(list))
    }

    fun loadNextTopRatedMovies() = viewModelScope.launch {
        upcomingCurrentPage += 1
        val movies = moviesRepository.downloadMovies(
            MoviesCollections.TOP_RATED, topRatedCurrentPage
        )
        val list = moviesTopRated.value + movies
        _moviesTopRated.emit(ArrayList(list))
    }

    fun loadNextNowPlayingMovies() = viewModelScope.launch {
        nowPlayingCurrentPage += 1
        val movies = moviesRepository.downloadMovies(
            MoviesCollections.NOW_PLAYING, nowPlayingCurrentPage
        )
        val list = moviesNowPlaying.value + movies
        _moviesNowPlaying.emit(ArrayList(list))
    }
}