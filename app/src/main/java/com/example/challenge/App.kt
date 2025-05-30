package com.example.challenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun App(navController: NavHostController) {
    val mainViewModel: MainViewModel = viewModel()
    val movieState by mainViewModel.movieState
    val tvShowState by mainViewModel.tvShowState

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController, movieState, tvShowState)
        }
        composable(Screen.MovieDetailScreen.route) {
            val movie = navController.previousBackStackEntry?.savedStateHandle?.get<Movie>("movie")
            movie?.let { MovieDetailScreen(it) }
        }
        composable(Screen.TvShowDetailScreen.route) {
            val tvShow = navController.previousBackStackEntry?.savedStateHandle?.get<TvShow>("tvshow")
            tvShow?.let { TvShowDetailScreen(it) }
        }
    }
}