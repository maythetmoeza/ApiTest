package com.example.retrofittesttwo.data

data class Credit(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int = 0
)