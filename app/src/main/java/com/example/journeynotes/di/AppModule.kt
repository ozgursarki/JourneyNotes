package com.example.journeynotes.di

import android.content.Context
import androidx.room.Room
import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.data.local.NoteDao
import com.example.journeynotes.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(context,
        NoteDatabase::class.java,
        "place_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePlaceRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteDao)
    }

    @Provides
    @Singleton
    fun providePlaceDao(placedb: NoteDatabase) : NoteDao {
        return placedb.placeDao()
    }

}