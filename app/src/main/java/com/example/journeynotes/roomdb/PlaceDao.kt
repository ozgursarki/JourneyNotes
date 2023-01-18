package com.example.journeynotes.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.journeynotes.model.Place

@Dao
interface PlaceDao {

    @Query("SELECT * FROM Place")
    fun getAllPlaces() : LiveData<List<Place>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlace(place: Place)

    @Delete
    suspend fun deletePlace(place: Place)
}