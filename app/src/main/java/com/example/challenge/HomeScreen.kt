package com.example.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeScreen(
    navController: NavController,
    movieState: MainViewModel.MovieState,
    tvShowState: MainViewModel.TvShowState,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Movies", "TV Shows")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1A237E)
    ) {
        Column(modifier = modifier.padding(top = 64.dp)) {
            Text(
                "Top Rated TvShows/Movies",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
            )

            TabRow(selectedTabIndex = selectedTab, containerColor = Color(0xFF1A237E),
                contentColor = Color.White,
                modifier = modifier.padding(bottom = 12.dp)){
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }
            when (selectedTab) {
                0 -> MovieGrid(movieState.list) { movie ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("movie", movie)
                    navController.navigate(Screen.MovieDetailScreen.route)
                }

                1 -> TvShowGrid(tvShowState.list) { tvShow ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("tvshow", tvShow)
                    navController.navigate(Screen.TvShowDetailScreen.route)
                }
            }
        }
    }
}

@Composable
fun MovieGrid(movies: List<Movie>, onClick: (Movie) -> Unit) {
    LazyColumn {
        items(movies) { movie ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(movie) }
                    .padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
                    contentDescription = movie.title,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(movie.title, style = MaterialTheme.typography.titleMedium, color = Color.White)
            }
        }
    }
}

@Composable
fun TvShowGrid(tvShows: List<TvShow>, onClick: (TvShow) -> Unit) {
    LazyColumn {
        items(tvShows) { tvShow ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(tvShow) }
                    .padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${tvShow.poster_path}"),
                    contentDescription = tvShow.name,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(tvShow.name, style = MaterialTheme.typography.titleMedium, color = Color.White)
            }
        }
    }
}