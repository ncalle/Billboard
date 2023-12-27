package com.justify.cartelera.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.justify.cartelera.MainActivity
import com.justify.cartelera.api.MovieModel
import com.justify.cartelera.MovieViewModel
import com.justify.cartelera.R

const val CAMERA_PERMISSION_REQUEST_CODE = 0
const val LOCATION_PERMISSION_REQUEST_CODE = 1

/**
 * MovieScreen will be in charge of displaying the list of movies.
 * */
@Composable
fun MovieScreen(viewModel: MovieViewModel, navController: NavController) {
    val movies by viewModel.movies.observeAsState(emptyList())
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.bilboard)) },
                actions = {
                    IconButton(onClick = { requestCameraPermission(context) }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_camera_img),
                            contentDescription = "Camera",
                        )
                    }
                    IconButton(onClick = { requestLocationPermission(context) }) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Location")
                    }
                }
            )
        },
        scaffoldState = scaffoldState,

    ) {

        LazyColumn {
            items(movies) { movie ->
                MovieItem(movie, onClick = {
                    try {
                        navController.navigate("movie/${movie.id}")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                })
            }
        }
    }
}


private fun requestCameraPermission(context: Context) {

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED
    ) {

        ActivityCompat.requestPermissions(
            context as MainActivity,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    } else {
        Toast.makeText(context, context.getString(R.string.camera_granted), Toast.LENGTH_SHORT)
            .show()
    }
}

private fun requestLocationPermission(context: Context) {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {

        ActivityCompat.requestPermissions(
            context as MainActivity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    } else {
        Toast.makeText(
            context,
            context.getString(R.string.location_granted),
            Toast.LENGTH_SHORT
        ).show()
    }
}


@Composable
fun MovieItem(movie: MovieModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {

        AsyncImage(
            model = movie.image,
            contentDescription = movie.title
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = movie.title, fontWeight = FontWeight.Bold)
            Text(text = movie.releaseState)
        }
    }
}



