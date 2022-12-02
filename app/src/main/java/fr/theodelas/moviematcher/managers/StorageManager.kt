package fr.theodelas.moviematcher.managers

import fr.theodelas.moviematcher.models.HomeCardModel

class StorageManager {

    companion object {
        lateinit var default: StorageManager
    }

    init {
        default = this
    }

    var favoriteCards = mutableListOf<HomeCardModel>()
        private set

    fun addFavorite(card: HomeCardModel) {
        favoriteCards.add(card)
    }

}