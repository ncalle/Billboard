package com.justify.cartelera.api

import com.justify.cartelera.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/movies.json")
    suspend fun getMovies(@Query("key") key: String): MovieResponse
}