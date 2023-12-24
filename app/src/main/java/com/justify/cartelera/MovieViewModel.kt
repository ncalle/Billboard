package com.justify.cartelera

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justify.cartelera.api.ApiService
import com.justify.cartelera.api.ServiceGenerator
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MovieViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<MovieModel>>()
    val movies: LiveData<List<MovieModel>> = _movies

    private val keyValue = "cb03b960"

    init {
        viewModelScope.launch {
            try {
                val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
                val call = serviceGenerator.getMovies(key = keyValue)
                Log.d("request", call.toString())

                var sortMovies = call.items.sortedWith(compareByDescending<MovieModel> { getYear(it.releaseState) }
                    .thenByDescending { getMonth(it.releaseState) }
                    .thenByDescending { getDay(it.releaseState) })
                Log.d("sortMovies", sortMovies.toString())
                _movies.value = sortMovies

            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error: ${e.message}")
            }
        }
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