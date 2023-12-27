package com.justify.cartelera

import com.justify.cartelera.api.Genre
import com.justify.cartelera.api.MovieModel
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * ValidatorTestTest will be in charge of validating the MovieModel.
 * */
@RunWith(JUnit4::class)
class ValidatorTestTest{

    @Test
    fun validateMovie() {
        val movie = MovieModel(
            id = "tt1234567",
            title = "The Movie",
            fullTitle = "The Movie (2021)",
            year = "2021",
            releaseState = "2021",
            image = "https://m.media-amazon.com/images/M/MV5BMTcxMjY0OTYyMl5BMl5BanBnXkFtZTYwMzYyNzY3._V1_.jpg",
            runtimeMins = "100",
            runtimeStr = "1h 40min",
            plot = "The plot",
            contentRating = "PG-13",
            imDbRating = "6.5",
            imDbRatingCount = "1234",
            metacriticRating = "N/A",
            genres = "Action, Adventure, Comedy",
            genreList = listOf(
                Genre("Action", "Action"),
                Genre("Adventure", "Adventure"),
                Genre("Comedy", "Comedy")
            ),
            directors = "John Doe",
            directorList = emptyList(),
            stars = "Jane Doe, John Doe",
            starList = emptyList()
        )
        assertTrue(ValidatorTest.validateMovie(movie))
    }

    @Test
    fun validateMovieEmptyTitle() {
        val movie = MovieModel(
            id = "tt1234567",
            title = "",
            fullTitle = "The Movie (2021)",
            year = "2021",
            releaseState = "2021",
            image = "https://m.media-amazon.com/images/M/MV5BMTcxMjY0OTYyMl5BMl5BanBnXkFtZTYwMzYyNzY3._V1_.jpg",
            runtimeMins = "100",
            runtimeStr = "1h 40min",
            plot = "The plot",
            contentRating = "PG-13",
            imDbRating = "6.5",
            imDbRatingCount = "1234",
            metacriticRating = "N/A",
            genres = "Action, Adventure, Comedy",
            genreList = listOf(
                Genre("Action", "Action"),
                Genre("Adventure", "Adventure"),
                Genre("Comedy", "Comedy")
            ),
            directors = "John Doe",
            directorList = emptyList(),
            stars = "Jane Doe, John Doe",
            starList = emptyList()
        )
        assertFalse(ValidatorTest.validateMovie(movie))
    }
}