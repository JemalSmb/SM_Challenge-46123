package com.example.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MovieDetailScreen(movie: Movie) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1A237E)
    ) {
        Column(modifier = Modifier.padding(16.dp).padding(top = 8.dp)) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
            )
            Spacer(Modifier.height(8.dp))
            Text(movie.title, style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Text(movie.overview, style = MaterialTheme.typography.bodyMedium, color = Color.White)
        }
    }
}