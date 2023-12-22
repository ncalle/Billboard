package com.justify.cartelera

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justify.cartelera.api.ApiService
import com.justify.cartelera.api.ServiceGenerator
import kotlinx.coroutines.launch

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
                _movies.value = call.items.sortedByDescending { it.releaseState } // call.items

            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error: ${e.message}")
            }
        }
    }
}