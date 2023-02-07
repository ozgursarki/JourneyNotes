package com.example.journeynotes.domain.di

import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)

object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideInsertNoteUseCase(repository: NoteRepository): InsertNote {
        return InsertNote(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetAllNotesUseCase(repository: NoteRepository): GetAllNotes {
        return GetAllNotes(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideDeleteNote(repository: NoteRepository): DeleteNote {
        return DeleteNote(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateNote(repository: NoteRepository) : EditNotes {
        return EditNotes(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNoteByID(repository: NoteRepository) : GetNoteByID {
        return GetNoteByID(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNoteByLocation(repository: NoteRepository) : GetNoteByLocation {
        return GetNoteByLocation(repository)
    }
}