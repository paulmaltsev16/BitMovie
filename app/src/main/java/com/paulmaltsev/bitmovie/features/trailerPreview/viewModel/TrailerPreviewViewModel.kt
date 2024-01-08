package com.paulmaltsev.bitmovie.features.trailerPreview.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.repositories.MoviesRepository
import com.paulmaltsev.bitmovie.core.repositories.MoviesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

private const val youTube = "YouTube"

class TrailerPreviewViewModel : ViewModel() {

    private val moviesRepository: MoviesRepository = MoviesRepositoryImpl(RetrofitClient)
    private var _youTubeKey = MutableSharedFlow<String?>()
    val youTubeKey get() = _youTubeKey.asSharedFlow()

    fun getFirstYoutubeVideoKey(movieId: String) = viewModelScope.launch(Dispatchers.IO) {
        val videos = moviesRepository.getMovieVideos(movieId)
        // There are more sites available, currently, only YouTube is supported.
        val youTubeVideo = videos?.firstOrNull { it.site == youTube }
        _youTubeKey.emit(youTubeVideo?.key)
    }
}