package com.example.journeynotes.data.mapper

import com.example.journeynotes.data.local.NoteEntity
import com.example.journeynotes.domain.model.Note

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        description = description,
        location = location
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        description = description,
        location = location
    )
}