package com.paulmaltsev.bitmovie.core.data.remote

import com.paulmaltsev.bitmovie.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIMEOUT_SECONDS: Long = 30

private class Headers {
    companion object {
        const val AUTHORIZATION = "Authorization"
        const val ACCEPT = "accept"
    }
}

object RetrofitClient {
    private var retrofit: Retrofit? = null
    val instance: Retrofit
        get() = retrofit ?: buildRetrofit().also { retrofit = it }

    private fun buildRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(initHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun initHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(initInterceptor())
        .build()

    private fun initInterceptor(): Interceptor = Interceptor { chain ->
        val request: Request = chain.request().newBuilder()
            .header(Headers.AUTHORIZATION, "Bearer " + BuildConfig.API_READ_ACCESS_TOKEN)
            .header(Headers.ACCEPT, "application/json")
            .build()
        chain.proceed(request)
    }
}