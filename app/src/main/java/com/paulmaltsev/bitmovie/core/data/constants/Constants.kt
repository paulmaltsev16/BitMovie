package com.paulmaltsev.bitmovie.core.data.constants

class Constants {
    companion object {
        const val SHARED_PREFERENCES_NAME = "movies_shared_preferences"
    }
}

class SharedPrefKeys {
    companion object {
        const val FAVORITE_MOVIES = "favorite_movies"
    }
}

class MoviesCollections {
    companion object {
        const val UPCOMING = "upcoming"
        const val TOP_RATED = "top_rated"
        const val NOW_PLAYING = "now_playing"
    }
}