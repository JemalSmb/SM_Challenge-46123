package com.example.challenge

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class TvShowResponse(
    val results: List<TvShow>
)

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    val original_name: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Float,
    val vote_count: Int,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val origin_country: List<String>,
    val original_language: String,
    val popularity: Float,
    val adult: Boolean
): Parcelable