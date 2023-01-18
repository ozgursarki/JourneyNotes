package com.example.journeynotes.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.journeynotes.domain.model.Location

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "description")
    var description : String,
    @ColumnInfo(name = "location")
    var location : Location
)