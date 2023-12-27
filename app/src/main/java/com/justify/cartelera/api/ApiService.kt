package com.justify.cartelera.api

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiService will be in charge of defining the endpoints of the API.
 * */
interface ApiService {
    @GET("/movies.json")
    suspend fun getMovies(@Query("key") key: String): MovieResponse
}