package com.example.challenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BookApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.booksState

    NavHost(navController = navController, startDestination = Screen.BookScreen.route) {
        composable(route = Screen.BookScreen.route) {
            BookScreen(viewstate = viewstate, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("bk", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route) {
            val book =
                navController.previousBackStackEntry?.savedStateHandle?.get<Book>("bk")
                    ?: Book("", "", "", "","")
            BookDetailScreen(book = book)
        }

    }
}