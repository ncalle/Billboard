package com.justify.cartelera.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.justify.cartelera.MovieModel
import com.justify.cartelera.MovieViewModel

@Composable
fun MovieScreen(viewModel: MovieViewModel, navController: NavController) {
    val movies by viewModel.movies.observeAsState(emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Cartelera") }) }
    ) {
        LazyColumn {
            items(movies) { movie ->
                MovieItem(movie, onClick = {
                    try {
                        Log.e("movie", movie.toString())
                        Log.e("Id", movie.id)
                        navController.navigate("movie/${movie.id}")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                })
            }
        }
    }
}

@Composable
fun MovieItem(movie: MovieModel, onClick: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {

        AsyncImage(
            model = movie.image,
            contentDescription = movie.title
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = movie.title, fontWeight = FontWeight.Bold)
            Text(text = movie.releaseState)
        }
    }
}
