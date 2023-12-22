package com.justify.cartelera

data class MovieResponse(
    val items: List<MovieModel>
)

data class MovieModel(
    val id: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val releaseState: String,
    val image: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val plot: String,
    val contentRating: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val metacriticRating: String,
    val genres: String,
    val genreList: List<Genre>,
    val directors: String,
    val directorList: List<Director>,
    val stars: String,
    val starList: List<Star>
)

data class Genre(
    val key: String,
    val value: String
)

data class Director(
    val id: String,
    val name: String
)

data class Star(
    val id: String,
    val name: String
)
