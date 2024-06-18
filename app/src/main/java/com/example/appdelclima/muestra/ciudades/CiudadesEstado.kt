package com.example.appdelclima.muestra.ciudades

import com.example.appdelclima.repository.modelos.Ciudad

sealed class CiudadesEstado {
    data object Vacio: CiudadesEstado()
    data object Cargando: CiudadesEstado()
    data class Resultado( val ciudades : List<Ciudad> ) : CiudadesEstado()
    data class Error(val mensaje: String): CiudadesEstado()

}