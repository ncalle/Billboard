package com.justify.cartelera

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justify.cartelera.api.ApiService
import com.justify.cartelera.api.MovieModel
import com.justify.cartelera.api.ServiceGenerator
import com.justify.cartelera.database.MovieRepository
import com.justify.cartelera.database.entities.MovieEntity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MovieViewModel(private val repository: MovieRepository, context: Context) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieModel>>()
    val movies: LiveData<List<MovieModel>> = _movies

    private val keyValue = "cb03b960"

    init {
        viewModelScope.launch {
            try {

                if(isConnected(context)){
                    val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
                    val call = serviceGenerator.getMovies(key = keyValue)
                    Log.d("MovieViewModel request", call.toString())

                    val sortMovies = call.items.sortedWith(compareByDescending<MovieModel> { getYear(it.releaseState) }
                        .thenByDescending { getMonth(it.releaseState) }
                        .thenByDescending { getDay(it.releaseState) })
                    Log.d("MovieViewModel sort", sortMovies.toString())
                    Log.d("MovieViewModel sort", sortMovies.size.toString())
                    _movies.value = sortMovies

                    val movies = sortMovies.map { movie ->
                        MovieEntity(
                            id = movie.id,
                            title = movie.title,
                            fullTitle = movie.fullTitle,
                            year = movie.year,
                            releaseState = movie.releaseState,
                            image = movie.image,
                            runtimeMins = movie.runtimeMins,
                            runtimeStr = movie.runtimeStr,
                            plot = movie.plot,
                            contentRating = movie.contentRating,
                            imDbRating = movie.imDbRating,
                            imDbRatingCount = movie.imDbRatingCount,
                            metacriticRating = movie.metacriticRating,
                            genres = movie.genres,
                            directorList = movie.directors,
                            stars = movie.stars
                        )
                    }
                    Log.e("MovieViewModel", repository.toString())
                    repository.insertMovies(movies)

                } else {
                    val savedList = repository.getMovies()

                    _movies.value = savedList.map { movie ->
                        MovieModel(
                            id = movie.id,
                            title = movie.title,
                            fullTitle = movie.fullTitle,
                            year = movie.year,
                            releaseState = movie.releaseState,
                            image = movie.image,
                            runtimeMins = movie.runtimeMins,
                            runtimeStr = movie.runtimeStr,
                            plot = movie.plot,
                            contentRating = movie.contentRating,
                            imDbRating = movie.imDbRating,
                            imDbRatingCount = movie.imDbRatingCount,
                            metacriticRating = movie.metacriticRating,
                            genres = movie.genres,
                            directors = movie.directorList,
                            stars = movie.stars,
                            genreList = emptyList(),
                            directorList = emptyList(),
                            starList = emptyList()
                        )
                    }
                }

            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error: ${e.message}")
            }
        }
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }

    private fun getDay(dateString: String): Int? {
        try {
            val format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val date = format.parse(dateString)

            if (date != null) {
                val calendar = java.util.Calendar.getInstance()
                calendar.time = date
                return calendar.get(java.util.Calendar.DAY_OF_MONTH)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    private fun getMonth(dateString: String): Int? {
        try {
            val format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val date = format.parse(dateString)

            if (date != null) {
                val calendar = java.util.Calendar.getInstance()
                calendar.time = date
                return calendar.get(java.util.Calendar.MONTH) + 1
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    private fun getYear(dateString: String): Int? {
        try {
            val format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val date = format.parse(dateString)

            if (date != null) {
                val calendar = java.util.Calendar.getInstance()
                calendar.time = date
                return calendar.get(java.util.Calendar.YEAR)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    fun getMovieById(id: String): MovieModel? {
        return _movies.value?.first { it.id == id }
    }
}