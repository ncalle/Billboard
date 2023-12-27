package com.justify.cartelera.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justify.cartelera.database.entities.MovieEntity

/**
 * MovieDao will be in charge of defining the queries to the database.
 * */
@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()

}