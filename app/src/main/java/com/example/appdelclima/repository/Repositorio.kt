package com.example.appdelclima.repository

import com.example.appdelclima.repository.modelos.Ciudad
import com.example.appdelclima.repository.modelos.Clima

interface Repositorio {
    suspend fun buscarCiudad(ciudad: String): List<Ciudad>
    suspend fun traerClima(lat: Float, lon: Float) : Clima
    suspend fun traerPronostico(lat: Float, lon: Float) : List<Clima>
}