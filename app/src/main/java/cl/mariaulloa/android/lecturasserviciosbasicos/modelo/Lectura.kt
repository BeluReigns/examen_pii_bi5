package cl.mariaulloa.android.lecturasserviciosbasicos.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

// definir nombre tabla, primary key y cada una de sus columnas)
@Entity(tableName = "lecturas")
data class Lectura(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: String,
    val valor: Int,
    val fecha: Long
)