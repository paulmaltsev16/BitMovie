package com.paulmaltsev.bitmovie.core.data.remote.api

import com.paulmaltsev.bitmovie.core.models.movie.MovieModel
import com.paulmaltsev.bitmovie.core.models.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("/3/movie/{movieCollection}")
    suspend fun getMovies(
        @Path("movieCollection") movieCollection: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieResponse?>?

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("language") language: String = "en-US"
    ): Response<MovieModel>
}