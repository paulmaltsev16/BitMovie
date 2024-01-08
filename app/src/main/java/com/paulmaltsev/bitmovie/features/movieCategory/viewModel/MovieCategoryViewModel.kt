package com.paulmaltsev.bitmovie.features.movieCategory.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.constants.MoviesCollectionType
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.core.repositories.MoviesRepository
import com.paulmaltsev.bitmovie.core.repositories.MoviesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieCategoryViewModel : ViewModel() {

    private val moviesRepository: MoviesRepository = MoviesRepositoryImpl(RetrofitClient)

    private var currentPage = 0
    private var _movies = MutableStateFlow(arrayListOf<MovieModel>())
    val movies get() = _movies.asStateFlow()

    fun downloadMovies(moviesCollectionType: MoviesCollectionType) {
        viewModelScope.launch(Dispatchers.IO) {
            currentPage += 1
            val movies = moviesRepository.downloadMovies(moviesCollectionType, currentPage)
            _movies.emit(movies)
        }
    }
}