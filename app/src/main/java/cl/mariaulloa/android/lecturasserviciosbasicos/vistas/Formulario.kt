package cl.mariaulloa.android.lecturasserviciosbasicos.vistas


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.mariaulloa.android.lecturasserviciosbasicos.modelo.Lectura
import cl.mariaulloa.android.lecturasserviciosbasicos.viewmodel.LecturaViewModel
import cl.mariaulloa.android.lecturasserviciosbasicos.util.formatFecha


@Composable
fun Formulario(navController: NavController, vm: LecturaViewModel = viewModel()) {
    var valor by remember { mutableStateOf("") }
    val fechaActual = remember { System.currentTimeMillis() } // Long
    val fechaFormateada = formatFecha(fechaActual) // String visible
    var tipoSeleccionado by remember { mutableStateOf("Agua") }

    val tipos = listOf("Agua", "Luz", "Gas")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Registro Medidor",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = valor,
            onValueChange = { valor = it },
            label = { Text("Medidor") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = fechaFormateada,
            onValueChange = {},
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false // para que no se edite, solo se muestre
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Medidor de:")
        tipos.forEach { tipo ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (tipoSeleccionado == tipo),
                    onClick = { tipoSeleccionado = tipo }
                )
                Text(text = tipo)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val valorEntero = valor.toIntOrNull() ?: 0
                if (valorEntero > 0) {
                    vm.agregarLectura(
                        Lectura(
                            tipo = tipoSeleccionado,
                            valor = valorEntero,
                            fecha = fechaActual // Guardamos el Long
                        )
                    )
                    navController.popBackStack()
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Registrar medición")
        }
    }
}