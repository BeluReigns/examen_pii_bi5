package cl.mariaulloa.android.lecturasserviciosbasicos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.mariaulloa.android.lecturasserviciosbasicos.data.AppDatabase
import cl.mariaulloa.android.lecturasserviciosbasicos.data.RepositorioLecturas
import cl.mariaulloa.android.lecturasserviciosbasicos.modelo.Lectura
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LecturaViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio: RepositorioLecturas
    private val _lecturas = MutableStateFlow<List<Lectura>>(emptyList())
    val lecturas: StateFlow<List<Lectura>> get() = _lecturas

    init {
        val dao = AppDatabase.obtenerInstancia(application).lecturaDao()
        repositorio = RepositorioLecturas(dao)

        viewModelScope.launch {
            repositorio.lecturas.collect { datos ->
                _lecturas.value = datos
            }
        }
    }

    fun agregarLectura(lectura: Lectura) {
        viewModelScope.launch {
            repositorio.agregar(lectura)
        }
    }
}
