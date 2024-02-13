package es.vag.vmusic.search_function

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("character")
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("image")
    val image: String,
    @ColumnInfo("status")
    val status: String
)
