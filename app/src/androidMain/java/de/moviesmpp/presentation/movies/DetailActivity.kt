package de.moviesmpp.presentation.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.R
const val MOVIE_ID: String = "extra_movie_id"

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}
