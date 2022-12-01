package fr.theodelas.moviematcher.model

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class HomeViewModel: ViewModel() {

    private val stream = MutableLiveData<HomeModel>()

    val modelStream: LiveData<HomeModel>
        get() = stream

    private val data = listOf(
        HomeCardModel(
            name = "Rodrigo Dominguez", age = 27, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#c50e29")
        ),
        HomeCardModel(
            name = "CerveChat Dominguez", age = 2, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#c60055")
        ),
        HomeCardModel(
            name = "Sofia Jerez Test", age = 27, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#aa00c7")
        ),
        HomeCardModel(
            name = "Maria Perez", age = 34, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#3f1dcb")
        ),
        HomeCardModel(
            name = "Rodrigo Dominguez", age = 27, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#0043ca")
        ),
        HomeCardModel(
            name = "Rodrigo Dominguez", age = 222, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#005ecb")
        ),
        HomeCardModel(
            name = "Perez Gonzalez", age = 45, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#00b686")
        ),
        HomeCardModel(
            name = "Tomas Dominguez", age = 43, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#00b248")
        ),
        HomeCardModel(
            name = "Rodrigo Dominguez", age = 44, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#32cb00")
        ),
        HomeCardModel(
            name = "Lopez Jose Jose", age = 87, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#90cc00")
        ),
        HomeCardModel(
            name = "Felipe Felipe Lopez", age = 23, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#c7b800")
        ),
        HomeCardModel(
            name = "Nicolas Lucas Test", age = 27, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#c79400")
        ),
        HomeCardModel(
            name = "John", age = 237, description = "Esto es una descripcion de ejemplo", backgroundColor = Color.parseColor("#c56200")
        )
    )
    private var currentIndex = 0

    private val topCard
        get() = data[currentIndex % data.size]
    private val bottomCard
        get() = data[(currentIndex + 1) % data.size]

    init {
        updateCards()
    }

    fun swipe() {
        currentIndex += 1
        updateCards()
    }

    private fun updateCards() {
        stream.value = HomeModel(
            cardTop = topCard,
            cardBottom = bottomCard
        )
    }

}