package com.justify.cartelera.database

import com.justify.cartelera.database.dao.MovieDao
import com.justify.cartelera.database.entities.MovieEntity

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.deleteAll()
        movieDao.insertMovies(movies)
    }

    suspend fun getMovies(): List<MovieEntity> {
        return movieDao.getMovies()
    }

}