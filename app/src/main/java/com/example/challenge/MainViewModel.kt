package com.example.challenge

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val _bookState = mutableStateOf(BookState())
    val booksState: State<BookState> = _bookState

    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {
            try {
                val response = bookService.getBooks()
                _bookState.value = _bookState.value.copy(
                    list = response.books,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _bookState.value = _bookState.value.copy(
                    loading = false,
                    error = "Error fetching Books ${e.message}"
                )
            }
        }
    }

    data class BookState(
        val loading: Boolean = true,
        val list: List<Book> = emptyList(),
        val error: String? = null
    )
}