package com.justify.cartelera

import com.justify.cartelera.api.MovieModel

/**
 * ValidatorTest will be in charge of validating the data received from the API.
 * */
object ValidatorTest {

    fun validateMovie(movie: MovieModel): Boolean {
        return movie.title.isNotEmpty() && movie.releaseState.isNotEmpty() && movie.id.isNotEmpty()
    }
}