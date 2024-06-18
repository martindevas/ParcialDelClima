package com.example.appdelclima.repository

import com.example.appdelclima.repository.modelos.Ciudad
import com.example.appdelclima.repository.modelos.Clima
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RepositorioApi : Repositorio {

    private val apiKey = "e335b568e210112640a2fff267d9f987"
    private val cliente = HttpClient(){
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun buscarCiudad(ciudad: String): List<Ciudad> {
        val respuesta = cliente.get("http://api.openweathermap.org/geo/1.0/direct"){
            parameter("q",ciudad)
            parameter("limit",5)
            parameter("appid",apiKey)
        }
        if (respuesta.status == HttpStatusCode.OK){
            val ciudades = respuesta.body<List<Ciudad>>()
            return ciudades
        }else{
            throw Exception()
        }
    }

    override suspend fun traerClima(lat: Float, lon: Float): Clima {
        val respuesta = cliente.get("https://api.openweathermap.org/data/2.5/weather"){
            parameter("lat",lat)
            parameter("lon",lon)
            parameter("units","metric")
            parameter("appid",apiKey)
        }
        if (respuesta.status == HttpStatusCode.OK){
            val clima = respuesta.body<Clima>()
            return clima
        }else{
            throw Exception()
        }
    }

    override suspend fun traerPronostico(lat: Float, lon: Float): List<Clima> {
        val respuesta = cliente.get("https://api.openweathermap.org/data/2.5/forecast/daily"){
            parameter("lat",lat)
            parameter("lon",lon)
            parameter("cnt", 5)
            parameter("appid",apiKey)
        }
        if(respuesta.status == HttpStatusCode.OK){
            val pronostico = respuesta.body<List<Clima>>()
            return pronostico
        }else{
            throw Exception()
        }
    }
}