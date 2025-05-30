package com.example.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun TvShowDetailScreen(tvShow: TvShow) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${tvShow.poster_path}"),
            contentDescription = tvShow.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
        )
        Spacer(Modifier.height(8.dp))
        Text(tvShow.name, style = MaterialTheme.typography.headlineMedium)
        Text(tvShow.overview, style = MaterialTheme.typography.bodyMedium)
    }
}