package com.paulmaltsev.bitmovie.features.home.repository

import android.util.Log
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.data.remote.api.MoviesApi
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel

private const val TAG = "MoviesRepository"

class MoviesRepository(
    private val client: RetrofitClient
) {
    suspend fun downloadMovies(movieCollection: String, page: Int): ArrayList<MovieModel> {
        return try {
            val api = client.instance.create(MoviesApi::class.java)
            val result = api.getMovies(movieCollection, page)
            result?.body()?.movies ?: arrayListOf()
        } catch (e: Exception) {
            Log.e(TAG, "downloadMovies, failed: ", e)
            arrayListOf()
        }
    }

    suspend fun getMovieDetailsById(id: String?): MovieModel? {
        id ?: return null
        return try {
            val api = client.instance.create(MoviesApi::class.java)
            val result = api.getMovieDetails(id)
            return result.body()
        } catch (e: Exception) {
            Log.e(TAG, "getMovieDetailsById, failed: ", e)
            null
        }
    }
}