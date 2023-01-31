package com.example.journeynotes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Note(
    var id: Int? = null,
    val title: String,
    val description: String,
    val location: Location,
    val date : LocalDate,
    val color: Int? = null,
) : Parcelable