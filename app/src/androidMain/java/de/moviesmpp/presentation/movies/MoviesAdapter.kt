package de.moviesmpp.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.R
import de.moviesmpp.domain.model.Movie



class MoviesAdapter constructor(private val onMovieSelected:
                                    (Movie, View) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var movies: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onMovieSelected)

    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val posterView: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        val titleView: TextView = itemView.findViewById(R.id.tv_movie_title)

        fun bind(movie: Movie, listener: (Movie, View) -> Unit) = with(itemView) {
            titleView.text = movie.title
            Glide.with(posterView).load(movie.completePosterPath).into(posterView)
            setOnClickListener { listener(movie, itemView) }
        }
    }
}