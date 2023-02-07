package com.example.journeynotes.data.local

import androidx.room.TypeConverter
import com.example.journeynotes.domain.model.Location
import com.google.gson.Gson

object Converters {

    @TypeConverter
    fun fromString(value: String?): Location {
        return Gson().fromJson(value, Location::class.java)
    }

    @TypeConverter
    fun fromLocation(location: Location): String? {
        return Gson().toJson(location)
    }
}