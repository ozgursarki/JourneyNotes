package com.example.journeynotes.domain.model


data class Note(
    var id: Int? = null,
    val title: String,
    val description: String,
    val location: Location,
)