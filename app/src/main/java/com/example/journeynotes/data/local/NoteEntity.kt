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
    @ColumnInfo(name = "note_day")
    val dayOfMonth: Int,
    @ColumnInfo(name = "note_month")
    val month: Int,
    @ColumnInfo(name = "note_year")
    val year: Int,
    @ColumnInfo(name = "note_color")
    val color: Int?,
)