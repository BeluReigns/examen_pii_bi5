package cl.mariaulloa.android.lecturasserviciosbasicos.util

import java.text.SimpleDateFormat
import java.util.*

fun formatFecha(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}