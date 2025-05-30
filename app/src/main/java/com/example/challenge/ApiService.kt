package com.example.challenge

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TmdbApiService {
    @Headers("Authorization: e1baaf1b2f17a0021c006faa1fba5c61")
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("language") language: String = "en-US", @Query("page") page: Int = 1): MovieResponse

    @Headers("Authorization: e1baaf1b2f17a0021c006faa1fba5c61")
    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(@Query("language") language: String = "en-US", @Query("page") page: Int = 1): TvShowResponse
}