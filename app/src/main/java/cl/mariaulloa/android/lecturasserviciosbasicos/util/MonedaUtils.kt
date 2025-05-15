package cl.mariaulloa.android.lecturasserviciosbasicos.util

import java.text.NumberFormat
import java.util.*

fun formatMonedaCLP(valor: Int): String {
    val formato = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
    return formato.format(valor)
}
