package com.example.journeynotes.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.journeynotes.data.PlaceRepository
import com.example.journeynotes.model.Place
import com.example.journeynotes.roomdb.PlaceDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceViewModel(application : Application) : AndroidViewModel(application) {

    val readAllPlace : LiveData<List<Place>>
    private val repository : PlaceRepository

    init {
        val placeDao = PlaceDatabase.getDatabase(application).placeDao()
        repository = PlaceRepository(placeDao)
        readAllPlace = repository.readAllPlace
    }

    fun addPlace(place: Place) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlace(place)
        }
    }

}