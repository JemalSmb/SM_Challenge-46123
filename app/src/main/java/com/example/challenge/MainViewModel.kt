package com.example.challenge

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val _movieState = mutableStateOf(MovieState())
    val movieState: State<MovieState> = _movieState

    private val _tvShowState = mutableStateOf(TvShowState())
    val tvShowState: State<TvShowState> = _tvShowState


    init {
        loadMovies()
        loadTvShows()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTopRatedMovies("e1baaf1b2f17a0021c006faa1fba5c61")
                _movieState.value = _movieState.value.copy(
                    list = response.results,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _movieState.value = _movieState.value.copy(
                    loading = false,
                    error = "Error fetching Movies: ${e.message}"
                )
            }
        }
    }

    private fun loadTvShows() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTopRatedTvShows("e1baaf1b2f17a0021c006faa1fba5c61")
                _tvShowState.value = _tvShowState.value.copy(
                    list = response.results,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _tvShowState.value = _tvShowState.value.copy(
                    loading = false,
                    error = "Error fetching TV Shows: ${e.message}"
                )
            }
        }
    }

    data class MovieState(
        val loading: Boolean = true,
        val list: List<Movie> = emptyList(),
        val error: String? = null
    )

    data class TvShowState(
        val loading: Boolean = true,
        val list: List<TvShow> = emptyList(),
        val error: String? = null
    )
}