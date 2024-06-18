package com.example.appdelclima.muestra.ciudades

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appdelclima.repository.modelos.Ciudad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CiudadesView(
    modifier: Modifier = Modifier,
    state: CiudadesEstado,
    onAction: (CiudadesIntencion) -> Unit
) {
    var value by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFBBDEFB))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = value,
            label = { Text(text = "Buscar por nombre", color = MaterialTheme.colorScheme.primary) },
            onValueChange = {
                value = it
                onAction(CiudadesIntencion.Buscar(value))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = MaterialTheme.colorScheme.primary)
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground,

                )
        )
        when (state) {
            CiudadesEstado.Cargando -> Text(text = "Cargando...", color = Color.Blue)
            is CiudadesEstado.Error -> Text(text = state.mensaje, color = Color.Red)
            is CiudadesEstado.Resultado -> ListaDeCiudades(state.ciudades){
                onAction(CiudadesIntencion.Seleccionar(it)) }
            CiudadesEstado.Vacio -> Text(text = "No hay resultados", color = Color.Red)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeCiudades(ciudades: List<Ciudad>, onSelect: (Ciudad)->Unit) {
    val cardColors = CardDefaults.cardColors(
        containerColor = Color(0xFF64B5F6)
    )
    LazyColumn(
        contentPadding = PaddingValues(vertical = 100.dp)
    ) {
        items(items = ciudades) {
            Card(onClick = { onSelect(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = cardColors
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${it.name}, ${it.country}",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCiudadesView() {
    CiudadesView(
        state = CiudadesEstado.Resultado(
            listOf(
                Ciudad("Buenos Aires", 22.2f,22.3f,null,"Argentina"),
                Ciudad("Córdoba", 22.2f,22.3f,null,"Argentina"),
                Ciudad("Montevideo", 22.2f,22.3f,null,"Uruguay"),
                Ciudad("Santiago", 22.2f,22.3f,null,"Chile")
            )
        ),
        onAction = {}
    )
}