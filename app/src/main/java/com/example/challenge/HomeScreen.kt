package com.example.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.res.painterResource


@Composable
fun BookScreen(
    modifier: Modifier = Modifier,
    viewstate: MainViewModel.BookState,
    navigateToDetail: (Book) -> Unit
) {
    var selectedFilter by remember { mutableStateOf("All") }

    val filterOptions = remember(viewstate.list) {
        listOf("All") + viewstate.list.map { it.genre }.distinct()
    }

    val filteredBooks = remember(viewstate.list, selectedFilter) {
        if (selectedFilter == "All") viewstate.list else viewstate.list.filter { it.genre == selectedFilter }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF0A0E2A)) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
            Text(
                "The Book Archive",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
            )

            FilterDropdown(selectedFilter, filterOptions) { selectedFilter = it }

            Box(Modifier.fillMaxSize()) {
                when {
                    viewstate.loading -> CircularProgressIndicator(Modifier.align(Alignment.Center), color = Color.White)
                    viewstate.error != null -> Text("ERROR OCCURRED: ${viewstate.error}", color = Color.Red, modifier = Modifier.align(Alignment.Center))
                    filteredBooks.isEmpty() -> Text("No books match the filter", color = Color.White, modifier = Modifier.align(Alignment.Center))
                    else -> BookGrid(filteredBooks, navigateToDetail)
                }
            }
        }
    }
}


@Composable
fun BookGrid(books: List<Book>, navigateToDetail: (Book) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(books) { book ->
            BookItem(book = book) { navigateToDetail(book) }
        }
    }
}


@Composable
fun BookItem(book: Book, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = book.image_url,
                error = painterResource(R.drawable.placeholder),
                placeholder = painterResource(R.drawable.placeholder)
            ),
            contentDescription = book.book_name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
        )
        Text(book.book_name, style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(top = 4.dp), maxLines = 2, color = Color.White)
        Text(book.book_author, style = MaterialTheme.typography.bodySmall, color = Color.LightGray)
    }
}

@Composable
fun FilterDropdown(selectedFilter: String, options: List<String>, onFilterSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).wrapContentSize(Alignment.TopStart)) {
        OutlinedButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF1A237E), contentColor = Color.White)
        ) {
            Text("Filter by: $selectedFilter")
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Filter", modifier = Modifier.size(16.dp))
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.background(Color(0xFF1A237E))) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, color = Color.White) },
                    onClick = {
                        onFilterSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
