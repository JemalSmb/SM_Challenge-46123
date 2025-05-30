package com.example.challenge

sealed class Screen(val route: String) {
    object HomeScreen : Screen("homescreen")
    object MovieDetailScreen : Screen("moviedetailscreen")
    object TvShowDetailScreen : Screen("tvshowdetailscreen")
}