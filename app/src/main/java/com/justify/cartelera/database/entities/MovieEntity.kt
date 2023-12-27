package com.justify.cartelera.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * MovieEntity will be in charge of defining the table of the database.
 * */
@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "key") val key: Int = 0,

    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "fullTitle") val fullTitle: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "releaseState") val releaseState: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "runtimeMins") val runtimeMins: String,
    @ColumnInfo(name = "runtimeStr") val runtimeStr: String,
    @ColumnInfo(name = "plot") val plot: String,
    @ColumnInfo(name = "contentRating") val contentRating: String,
    @ColumnInfo(name = "imDbRating") val imDbRating: String,
    @ColumnInfo(name = "imDbRatingCount") val imDbRatingCount: String,
    @ColumnInfo(name = "metacriticRating") val metacriticRating: String,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "directors") val directorList: String,
    @ColumnInfo(name = "stars") val stars: String,
)