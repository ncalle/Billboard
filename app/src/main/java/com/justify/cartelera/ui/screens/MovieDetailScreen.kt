package com.justify.cartelera.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.justify.cartelera.MovieModel

@Composable
fun MovieDetailScreen(movieId: String, navController: NavController) { // movie: MovieModel) {
    val movie = MovieModel(
        id = "1",
        title = "Movie 1",
        image = "https://picsum.photos/200/300",
        releaseState = "2021-01-01",
        plot = "Plot 1",
        stars = "Stars 1",
        directors = "Director 1",
        genres = "Genre 1",
        fullTitle = "Full Plot 1",
        year = "2021",
        runtimeMins = "120",
        runtimeStr = "120",
        contentRating = "PG-13",
        imDbRating = "8.0",
        imDbRatingCount = "100",
        metacriticRating = "80",
        genreList = emptyList(),
        directorList = emptyList(),
        starList = emptyList()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Fecha de estreno: ${movie.releaseState}",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Reseña: ${movie.plot}",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Actores: ${movie.stars}",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        AsyncImage(
            model = movie.image,
            contentDescription = movie.title,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


