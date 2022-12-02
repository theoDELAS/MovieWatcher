package fr.theodelas.moviematcher.models
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import fr.theodelas.moviematcher.managers.APIClient


class HomeViewModel: ViewModel() {

    private val stream = MutableLiveData<HomeModel>()

    val modelStream: LiveData<HomeModel>
        get() = stream
    
    private var data = emptyList<HomeCardModel>()
    private var currentIndex = 0
    private val apiClient = APIClient()

    init {
        updateCards()
    }

    fun swipe() {
        currentIndex += 1
        updateCards()
    }

    private fun updateCards() {
        stream.value = HomeModel(
            cardTop = getTopCard(),
            cardBottom = getBottomCard()
        )
    }

    fun getTopCard(): HomeCardModel? {
        if (data.isNotEmpty()) {
           return data[currentIndex % data.size]
        }
        return null
    }

    fun getBottomCard(): HomeCardModel? {
        if (data.isNotEmpty()) {
            return data[(currentIndex + 1) % data.size]
        }
        return null
    }

    suspend fun onViewCreated() {
        fetchPopularMovies()
    }

    private suspend fun fetchPopularMovies() {
        val fetchedResults = apiClient.getPopularMovies()

        this.data = fetchedResults?.results?.map {
            HomeCardModel(
                title = it.title,
                description = it.overview,
                releaseDate = it.release_date,
                imagePath = apiClient.baseImageUrl + it.poster_path)
        }?: emptyList()
    }
}