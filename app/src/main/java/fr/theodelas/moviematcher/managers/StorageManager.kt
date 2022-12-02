package fr.theodelas.moviematcher.managers

import fr.theodelas.moviematcher.home.HomeCardModel

class StorageManager {

    companion object {
        val default = StorageManager()
    }

    var favoriteCards = mutableListOf<HomeCardModel>()
        private set

    fun addFavorite(card: HomeCardModel) {
        favoriteCards.add(card)
    }

}