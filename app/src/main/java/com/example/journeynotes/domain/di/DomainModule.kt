package com.example.journeynotes.domain.di

import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.domain.use_case.GetAllNotes
import com.example.journeynotes.domain.use_case.InsertNote
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
    fun provideInsertNoteUseCase(repository: NoteRepository) : InsertNote {
        return InsertNote(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetAllNotesUseCase(repository: NoteRepository) : GetAllNotes {
        return GetAllNotes(repository)
    }
}