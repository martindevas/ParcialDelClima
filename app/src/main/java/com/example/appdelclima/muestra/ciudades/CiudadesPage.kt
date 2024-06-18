package com.example.appdelclima.muestra.ciudades

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appdelclima.repository.RepositorioApi

@Composable
fun CiudadesPage(
    navHostController: NavHostController
) {
    val viewModel : CiudadesViewModel = viewModel(
        factory = CiudadesViewModelFactory(
            repositorio = RepositorioApi(),
            router = Enrutador(navHostController)
        )
    )
    CiudadesView(
        state = viewModel.uiState,
        onAction = { intencion ->
            viewModel.ejecutar(intencion)
        }
    )
}
