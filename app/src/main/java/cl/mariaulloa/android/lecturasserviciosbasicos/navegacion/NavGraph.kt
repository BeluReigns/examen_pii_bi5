package cl.mariaulloa.android.lecturasserviciosbasicos.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.mariaulloa.android.lecturasserviciosbasicos.vistas.Formulario
import cl.mariaulloa.android.lecturasserviciosbasicos.vistas.Listado


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "listado") {
        composable("listado") { Listado(navController) }
        composable("formulario") { Formulario(navController) }
    }
}