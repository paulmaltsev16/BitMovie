package com.paulmaltsev.bitmovie.features.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.data.remote.api.MoviesApi
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.core.models.movie.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.GET

class HomeViewModel : ViewModel() {

    private var _movies = MutableStateFlow(arrayListOf<MovieModel>())
    val movies = _movies.asStateFlow()

    init {
        downloadMovies()
    }

    private fun downloadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val api = RetrofitClient.instance.create(MoviesApi::class.java)
            val result = api.getMovies()
            Log.i("tester", "movies: ${result.body()?.movies}")
            _movies.emit(result.body()?.movies ?: arrayListOf())
        }
    }
}