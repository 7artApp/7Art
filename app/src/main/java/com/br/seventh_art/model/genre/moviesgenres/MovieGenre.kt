package com.br.seventh_art.model.genre.moviesgenres


import com.google.gson.annotations.SerializedName

data class MovieGenre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)