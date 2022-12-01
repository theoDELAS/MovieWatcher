package fr.theodelas.moviematcher.models
import kotlinx.serialization.Serializable


@Serializable
data class FetchedMoviesDTO(
    val results: List<FetchedMovieDTO>
)

@Serializable
data class FetchedMovieDTO(
    val title: String,
    var overview: String,
    val release_date: String
)
