package com.paulmaltsev.bitmovie.core.data.constants

import com.paulmaltsev.bitmovie.R

class Constants {
    companion object {
        const val SHARED_PREFERENCES_NAME = "movies_shared_preferences"
        const val YOU_TUBE_BASE_PREVIEW_URL = "https://www.youtube.com/watch?v=%s"
    }
}

class SharedPrefKeys {
    companion object {
        const val FAVORITE_MOVIES = "favorite_movies"
    }
}

enum class MoviesCollectionType(
    val collectionName: String,
    val screenTitleId: Int
) {
    UPCOMING("upcoming", R.string.upcoming),
    TOP_RATED("top_rated", R.string.top_rated),
    NOW_PLAYING("now_playing", R.string.now_playing)
}