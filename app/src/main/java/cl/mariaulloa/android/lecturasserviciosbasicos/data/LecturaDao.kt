package cl.mariaulloa.android.lecturasserviciosbasicos.data

import androidx.room.*
import cl.mariaulloa.android.lecturasserviciosbasicos.modelo.Lectura
import kotlinx.coroutines.flow.Flow

@Dao
interface LecturaDao {
    // Consulta de todas las lecturas
    @Query("SELECT * FROM lecturas ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<Lectura>>

    // corrutina para agregar Lectura
    @Insert
    suspend fun insertar(lectura: Lectura)
}