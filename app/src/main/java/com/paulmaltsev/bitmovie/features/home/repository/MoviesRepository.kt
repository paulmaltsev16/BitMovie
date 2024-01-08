package com.paulmaltsev.bitmovie.features.home.repository

import android.util.Log
import com.paulmaltsev.bitmovie.core.data.constants.MoviesCollectionType
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.data.remote.api.MoviesApi
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel

private const val TAG = "MoviesRepository"

interface MoviesRepository {
    suspend fun downloadMovies(movieCollection: MoviesCollectionType, page: Int): ArrayList<MovieModel>
    suspend fun getMovieDetailsById(id: String?): MovieModel?
}

class MoviesRepositoryImpl(private val client: RetrofitClient) : MoviesRepository {
    override suspend fun downloadMovies(movieCollection: MoviesCollectionType, page: Int): ArrayList<MovieModel> {
        return try {
            val api = client.instance.create(MoviesApi::class.java)
            val result = api.getMovies(movieCollection.collectionName, page)
            (result?.body()?.movies ?: arrayListOf()).also {
                it.forEach{movie->
                    Log.i("tester", "id- ${movie.id}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "downloadMovies, failed: ", e)
            arrayListOf()
        }
    }

    override suspend fun getMovieDetailsById(id: String?): MovieModel? {
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