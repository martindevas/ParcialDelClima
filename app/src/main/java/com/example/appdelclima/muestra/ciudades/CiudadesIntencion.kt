package com.example.appdelclima.muestra.ciudades

import com.example.appdelclima.repository.modelos.Ciudad

sealed class CiudadesIntencion {
    data class Buscar( val nombre:String ) : CiudadesIntencion()
    data class Seleccionar(val ciudad: Ciudad) : CiudadesIntencion()
}
