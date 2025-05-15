package cl.mariaulloa.android.lecturasserviciosbasicos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.mariaulloa.android.lecturasserviciosbasicos.modelo.Lectura

@Database(entities = [Lectura::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lecturaDao(): LecturaDao

    companion object {
        @Volatile
        private var instancia: AppDatabase? = null

        fun obtenerInstancia(context: Context): AppDatabase {
            return instancia ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "lecturas_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instancia = it }
            }
        }
    }
}