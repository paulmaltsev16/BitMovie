package com.paulmaltsev.bitmovie.features.favorites.repository

import android.content.SharedPreferences
import com.paulmaltsev.bitmovie.core.data.constants.SharedPrefKeys
import com.paulmaltsev.bitmovie.core.extensions.fromJson
import com.paulmaltsev.bitmovie.core.extensions.toJson
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel

interface FavoriteRepository {
    fun updateFavorites(movie: MovieModel)
    fun isFavoriteMovie(movieId: Int?): Boolean
    fun getMoviesFromPreferences(): ArrayList<MovieModel>
}

class FavoriteRepositoryImpl(private val preferences: SharedPreferences) : FavoriteRepository {

    override fun updateFavorites(movie: MovieModel) {
        val movieId = movie.id ?: return
        if (isFavoriteMovie(movieId)) {
            removeMovieFromFavorite(movieId)
        } else {
            addMovieToFavorites(movie)
        }
    }

    override fun isFavoriteMovie(movieId: Int?): Boolean {
        val movies = getMoviesFromPreferences()
        val storedObject = movies.firstOrNull {
            it.id == movieId
        }
        return storedObject != null
    }

    override fun getMoviesFromPreferences(): ArrayList<MovieModel> {
        val json = preferences.getString(SharedPrefKeys.FAVORITE_MOVIES, "")
        val movies = fromJson<MovieModel>(json ?: "") ?: listOf()
        return ArrayList(movies)
    }

    private fun removeMovieFromFavorite(movieId: Int) {
        val movies = getMoviesFromPreferences()
        movies.removeIf {
            it.id == movieId
        }
        saveMoviesToPreferences(movies.toJson())
    }

    private fun addMovieToFavorites(movie: MovieModel) {
        val movies = getMoviesFromPreferences()
        movies.add(movie)
        saveMoviesToPreferences(movies.toJson())
    }

    private fun saveMoviesToPreferences(newJson: String) {
        preferences.edit().putString(SharedPrefKeys.FAVORITE_MOVIES, newJson).apply()
    }
}