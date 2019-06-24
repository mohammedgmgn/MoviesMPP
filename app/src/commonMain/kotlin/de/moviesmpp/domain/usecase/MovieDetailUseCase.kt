package de.moviesmpp.domain.usecase

import de.moviesmpp.data.MoviesApi
import de.moviesmpp.data.entity.MovieEntity
import de.moviesmpp.data.local.MoviesRepository
import de.moviesmpp.domain.model.*
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

private val json = "{\n" +
        "            \"popularity\": 437.513,\n" +
        "            \"id\": 299534,\n" +
        "            \"video\": false,\n" +
        "            \"vote_count\": 73,\n" +
        "            \"vote_average\": 7.4,\n" +
        "            \"title\": \"Avengers: Endgame\",\n" +
        "            \"release_date\": \"2019-04-26\",\n" +
        "            \"original_language\": \"en\",\n" +
        "            \"original_title\": \"Avengers: Endgame\",\n" +
        "            \"genre_ids\": [\n" +
        "                12,\n" +
        "                878,\n" +
        "                28\n" +
        "            ],\n" +
        "            \"backdrop_path\": \"/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg\",\n" +
        "            \"adult\": false,\n" +
        "            \"overview\": \"After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.\",\n" +
        "            \"poster_path\": \"/or06FN3Dka5tukK1e9sl16pB3iy.jpg\"\n" +
        "        }"

class MovieDetailUseCase(private val moviesRepository: MoviesRepository) : UseCase<MovieEntity, UseCase.None>() {
    @UnstableDefault
    override suspend fun run(params: None): Either<Exception, MovieEntity> {
        return try {
            val movie = Json.parse(MovieEntity.serializer(), json)
            Success(movie)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}