package com.example.journeynotes.data

import androidx.lifecycle.LiveData
import com.example.journeynotes.model.Place
import com.example.journeynotes.roomdb.PlaceDao

class PlaceRepository(private val placeDao: PlaceDao) {

    val readAllPlace : LiveData<List<Place>> = placeDao.getAllPlaces()

    suspend fun addPlace(place: Place) {
        placeDao.addPlace(place)
    }
}