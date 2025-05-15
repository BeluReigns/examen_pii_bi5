package cl.mariaulloa.android.lecturasserviciosbasicos.data

import cl.mariaulloa.android.lecturasserviciosbasicos.modelo.Lectura
import kotlinx.coroutines.flow.Flow

class RepositorioLecturas(private val dao: LecturaDao) {
    val lecturas: Flow<List<Lectura>> = dao.obtenerTodas()

    suspend fun agregar(lectura: Lectura) {
        dao.insertar(lectura)
    }
}
