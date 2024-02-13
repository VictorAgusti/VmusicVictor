package es.vag.vmusic.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.vag.vmusic.search_function.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getAll(): MutableList<Character>

    @Insert
    suspend fun insert(character: Character)

}