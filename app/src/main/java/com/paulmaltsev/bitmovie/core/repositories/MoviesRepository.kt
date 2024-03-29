package com.paulmaltsev.bitmovie.core.repositories

import android.util.Log
import com.paulmaltsev.bitmovie.core.data.constants.MoviesCollectionType
import com.paulmaltsev.bitmovie.core.data.remote.RetrofitClient
import com.paulmaltsev.bitmovie.core.data.remote.api.MoviesApi
import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.core.models.videos.VideoModel

private const val TAG = "MoviesRepository"

interface MoviesRepository {
    suspend fun downloadMovies(movieCollection: MoviesCollectionType, page: Int): ArrayList<MovieModel>
    suspend fun getMovieDetailsById(id: String?): MovieModel?
    suspend fun getMovieVideos(id: String?): ArrayList<VideoModel>?
}

class MoviesRepositoryImpl(private val client: RetrofitClient) : MoviesRepository {
    override suspend fun downloadMovies(
        movieCollection: MoviesCollectionType, page: Int
    ): ArrayList<MovieModel> {
        return try {
            val api = client.instance.create(MoviesApi::class.java)
            val result = api.getMovies(movieCollection.collectionName, page)
            result?.body()?.movies ?: arrayListOf()
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

    override suspend fun getMovieVideos(id: String?): ArrayList<VideoModel>? {
        id ?: return null
        return try {
            val api = client.instance.create(MoviesApi::class.java)
            val result = api.getMovieVideos(id)
            return result.body()?.videos
        } catch (e: Exception) {
            Log.e(TAG, "getMovieDetailsById, failed: ", e)
            null
        }
    }
}