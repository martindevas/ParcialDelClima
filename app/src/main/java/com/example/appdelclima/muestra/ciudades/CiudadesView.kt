package com.example.appdelclima.muestra.ciudades

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appdelclima.R
import com.example.appdelclima.repository.modelos.Ciudad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CiudadesView(
    modifier: Modifier = Modifier,
    state: CiudadesEstado,
    onAction: (CiudadesIntencion) -> Unit
) {
    var value by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF81C784)) // Color de fondo verde cálido
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Buscador(
            value = value,
            onValueChange = {
                value = it
                onAction(CiudadesIntencion.Buscar(it.text))
            }
        )
        when (state) {
            CiudadesEstado.Cargando -> Text(text = "Cargando...", color = Color.White)
            is CiudadesEstado.Error -> Text(text = state.mensaje, color = Color.Red)
            is CiudadesEstado.Resultado -> {
                ListaDeCiudades(state.ciudades) { onAction(CiudadesIntencion.Seleccionar(it)) }
            }
            CiudadesEstado.Vacio -> Text(text = "No hay resultados", color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Buscador(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "Buscar por nombre", color = Color.White) },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color.White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(Color(0xFF388E3C), shape = MaterialTheme.shapes.medium)
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                clip = true
            },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            cursorColor = Color.White,
            focusedLabelColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeCiudades(ciudades: List<Ciudad>, onSelect: (Ciudad) -> Unit) {
    val cardColors = CardDefaults.cardColors(
        containerColor = Color(0xFFA5D6A7) // Color verde claro para las tarjetas
    )
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(items = ciudades) { ciudad ->
            Card(onClick = { onSelect(ciudad) },
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
                        text = "${ciudad.name}, ${ciudad.country}",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewCiudadesView() {
    CiudadesView(
        state = CiudadesEstado.Resultado(
            listOf(
                Ciudad("Buenos Aires", 22.2f, 22.3f, null, "Argentina"),
                Ciudad("Córdoba", 22.2f, 22.3f, null, "Argentina"),
                Ciudad("Montevideo", 22.2f, 22.3f, null, "Uruguay"),
                Ciudad("Santiago", 22.2f, 22.3f, null, "Chile")
            )
        ),
        onAction = {}
    )
}