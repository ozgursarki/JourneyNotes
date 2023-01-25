package com.example.journeynotes.domain.model

import java.time.LocalDate


data class Note(
    var id: Int? = null,
    val title: String,
    val description: String,
    val location: Location,
    val date : LocalDate,
    val color: Int? = null,
)