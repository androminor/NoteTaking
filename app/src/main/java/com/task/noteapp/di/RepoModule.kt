package com.task.noteapp.di

import com.task.noteapp.data.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton


@InstallIn(ActivityComponent::class)
@Module
abstract class RepoModule {
    @Singleton
    @Binds
    abstract fun provideRepository(notesRepository: NotesRepository): NotesRepository
}