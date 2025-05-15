package cl.mariaulloa.android.lecturasserviciosbasicos.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.mariaulloa.android.lecturasserviciosbasicos.R
import cl.mariaulloa.android.lecturasserviciosbasicos.modelo.Lectura
import cl.mariaulloa.android.lecturasserviciosbasicos.util.formatFecha
import cl.mariaulloa.android.lecturasserviciosbasicos.util.formatMonedaCLP
import cl.mariaulloa.android.lecturasserviciosbasicos.viewmodel.LecturaViewModel


@Composable
fun Listado(navController: NavController, vm: LecturaViewModel = viewModel()) {
    val lista by vm.lecturas.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("formulario")
            }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            if (lista.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(id = R.string.sin_registros))
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(lista) { lectura ->
                        ItemLectura(lectura)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemLectura(lectura: Lectura) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = when (lectura.tipo.lowercase()) {
                    "agua", "water" -> Icons.Default.WaterDrop
                    "luz", "electricity" -> Icons.Default.Lightbulb
                    "gas" -> Icons.Default.LocalGasStation
                    else -> Icons.Default.WaterDrop
                },
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = lectura.tipo.replaceFirstChar { it.uppercase() })
        }

        Text(text = formatMonedaCLP(lectura.valor))
        Text(text = formatFecha(lectura.fecha))
    }

    HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
}
