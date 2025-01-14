package de.moviesmpp.data

import de.moviesmpp.data.entity.PopularMoviesEntity
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

private const val BASE_URL = "api.themoviedb.org/3/movie"
private const val HEADER_AUTHORIZATION = "Authorization"
private const val API_KEY = "357955f67e69d522c2235c5baac2e4c3"

@Suppress("EXPERIMENTAL_API_USAGE")
class MoviesApi(clientEngine: HttpClientEngine) {

    private val client = HttpClient(clientEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getPopularMovies(): PopularMoviesEntity {
        // Actually we're able to just return the get()-call and Ktor's JsonFeature will automatically do the
        // JSON parsing for us. However, this currently doesn't work with Kotlin/Native as it doesn't support reflection
        // and we have to manually use PopularMoviesEntity.serializer()
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = "/popular"
                parameter("api_key", API_KEY)
            }
        }

        val jsonBody = response.readText()

        return Json.parse(PopularMoviesEntity.serializer(), jsonBody)
    }
}

private fun String.asBearerToken() = "Bearer $this"