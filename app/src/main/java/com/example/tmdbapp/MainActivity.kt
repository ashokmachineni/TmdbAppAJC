package com.example.tmdbapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.tmdbapp.presenter.MoviesViewModel
import com.example.tmdbapp.ui.theme.TmdbAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val states = viewModel.movieState.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(states.isSuccess){state ->
                MovieList(result = state)
            }
        }
    }
    if(states.isLoading) {
        Log.d("ashok","loading")
        Row(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green)) {
            Text(text = "Loading")
        }
    }

}

@Composable
fun MovieList(result: com.example.tmdbapp.data.movieData.Result){
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500"+result.poster_path),
                contentDescription = "gfg image",

                modifier = Modifier
                    .wrapContentSize()
                    .height(180.dp)
                    .width(300.dp)
            )

            Text(text = result.original_title)

        }

    }
}



