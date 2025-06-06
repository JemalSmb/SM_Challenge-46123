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
fun TvShowDetailScreen(tvShow: TvShow) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1A237E)
    ) {
        Column(modifier = Modifier.padding(16.dp).padding(top = 8.dp)) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${tvShow.poster_path}"),
                contentDescription = tvShow.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f),
            )
            Spacer(Modifier.height(8.dp))
            Text(tvShow.name, style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Text(tvShow.overview, style = MaterialTheme.typography.bodyMedium, color = Color.White)
        }
    }
}