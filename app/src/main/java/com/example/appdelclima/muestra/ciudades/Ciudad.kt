package com.example.appdelclima.muestra.ciudades

import kotlinx.serialization.Serializable

@Serializable
data class Ciudad(
    val name: String,
    val lat: Float,
    val lon: Float,
    val state:  String? = null,
    val country: String
)
