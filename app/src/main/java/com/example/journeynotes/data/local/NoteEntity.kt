package com.example.journeynotes.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.journeynotes.domain.model.Location

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "note_title")
    var title: String,
    @ColumnInfo(name = "note_description")
    var description: String,
    @ColumnInfo(name = "note_location")
    var location: Location,
    @ColumnInfo(name = "note_date")
    val timeStamp: Long,
    @ColumnInfo(name = "note_color")
    val color: Int?
)