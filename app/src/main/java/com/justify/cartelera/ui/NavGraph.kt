package com.justify.cartelera.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.justify.cartelera.MovieViewModel
import com.justify.cartelera.database.MovieRepository
import com.justify.cartelera.ui.screens.MovieDetailScreen
import com.justify.cartelera.ui.screens.MovieScreen

/**
 * NavGraph will be in charge of defining the navigation graph.
 * */
@Composable
fun NavGraph (viewModel: MovieViewModel){

    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = "/") {
        composable("/") {
            MovieScreen(viewModel, navigationController)
        }
        composable("movie/{movieId}") {
            val movieId = it.arguments?.getString("movieId")
            MovieDetailScreen(movieId = movieId ?: "", viewModel)
        }
    }
}