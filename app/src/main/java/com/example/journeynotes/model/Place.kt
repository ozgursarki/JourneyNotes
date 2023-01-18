package com.example.journeynotes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.material.textfield.TextInputLayout

@Entity
data class Place(
    @ColumnInfo(name = "title")
    var name : String,
    @ColumnInfo(name = "description")
    var description : String
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}