package com.example.challenge

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Book(
    val book_name: String,
    val book_author: String,
    val genre: String,
    val synopsis: String,
    val image_url: String
): Parcelable

data class BooksResponse(
    @SerializedName("Books")
    val books: List<Book>
)
