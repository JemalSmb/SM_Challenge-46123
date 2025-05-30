package com.example.challenge

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val results: List<Movie>
)

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Float,
    val vote_count: Int,
    val release_date: String,
    val genre_ids: List<Int>,
    val adult: Boolean,
    val original_language: String,
    val popularity: Float,
    val video: Boolean
): Parcelable