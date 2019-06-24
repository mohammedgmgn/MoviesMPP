package de.moviesmpp.presentation.popularmovies

import de.moviesmpp.data.entity.MovieEntity
import de.moviesmpp.domain.defaultDispatcher
import de.moviesmpp.domain.model.Movie
import de.moviesmpp.domain.usecase.GetPopularMovies
import de.moviesmpp.domain.usecase.MovieDetailUseCase
import de.moviesmpp.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class MoviesDetailesPresenter(
    private val detailUseCase: MovieDetailUseCase,
    coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<PopularMovieDetailView>(coroutineContext) {


}


interface PopularMovieDetailView {

    fun setMovieDetail(movie: MovieEntity)

}