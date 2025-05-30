package com.example.challenge

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://my-json-server.typicode.com/JemalSmb/json_data/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val bookService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("db")
    suspend fun getBooks(): BooksResponse
}