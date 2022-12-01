package fr.theodelas.moviematcher.model

import androidx.annotation.ColorInt

data class HomeCardModel(
    val name: String,
    val age: Int,
    val description: String,
    @ColorInt val backgroundColor: Int
)