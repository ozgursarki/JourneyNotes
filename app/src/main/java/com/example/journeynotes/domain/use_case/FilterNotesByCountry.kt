package com.example.journeynotes.domain.use_case

import android.location.Address
import android.location.Geocoder
import com.example.journeynotes.domain.model.Note
import java.io.IOException

class FilterNotesByCountry(

) {

    operator fun invoke(
        geocoder: Geocoder,
        notes: List<Note>,
        country: String
    ): ArrayList<Note> {
        try {
            val newNotes = arrayListOf<Note>()
            notes.forEach { note ->
                val addresses: List<Address>? = geocoder.getFromLocation(note.location.latitude!!, note.location.longitude!!, 1)
                if (addresses != null && addresses.isNotEmpty()) {
                    val country_name = addresses[0].countryName
                    if (country == country_name) {
                        newNotes.add(note)
                    }
                }
            }
            return newNotes
        } catch (e: IOException) {
            e.printStackTrace()
            return arrayListOf()
        }
    }

}