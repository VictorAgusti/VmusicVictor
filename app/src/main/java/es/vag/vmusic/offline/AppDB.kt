package es.vag.vmusic.offline

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.vag.vmusic.search_function.Character

@Database(
    entities = [Character::class],
    version = 1
)
abstract class AppDB : RoomDatabase() {
    abstract fun characterDao() : CharacterDao


    companion object{
        private const val DATABASE_NAME = "APP_DB2"

        private var instance: AppDB? = null
        fun getInstance(context: Context): AppDB{
            synchronized(this){
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return instance!!
        }
    }
}
