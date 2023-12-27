package com.justify.cartelera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.justify.cartelera.database.MovieRepository
import com.justify.cartelera.database.entities.MovieDatabase
import com.justify.cartelera.ui.NavGraph
import com.justify.cartelera.ui.theme.CarteleraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CarteleraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val context = LocalContext.current
                    val movieDao = MovieDatabase.getInstance(context).movieDao()
                    val repository = MovieRepository(movieDao)
                    val viewModel = MovieViewModel(repository, context)
                    NavGraph(viewModel)
                }
            }
        }

    }

}