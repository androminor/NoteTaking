package com.task.noteapp.di

import com.task.noteapp.viewmodel.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
abstract class ViewmodelModule {
    @Singleton
    @Binds
    abstract fun provideViewmodel(viewModel: NotesViewModel): NotesViewModel
}