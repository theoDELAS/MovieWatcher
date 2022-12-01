package fr.theodelas.moviematcher.managers
import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import fr.theodelas.moviematcher.models.FetchedMoviesDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET


interface TMDBService {
    @GET("/3/movie/popular?api_key=5d8360a5f15b2cd55f7d1b697a12facc")
    suspend fun getPopularMovies(): FetchedMoviesDTO?
}

class APIClient {

    private val baseUrl = "https://api.themoviedb.org"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType())
        )
        .build()

    private val service = retrofit.create(TMDBService::class.java)

    suspend fun getPopularMovies(): FetchedMoviesDTO? {
        return try {
        service.getPopularMovies()
        } catch(e:java.lang.Exception) {
            Log.e("APIClient", e.toString())
            return null
        }
    }

}