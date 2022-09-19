package com.example.travelnotes.di.module

import com.example.travelnotes.data.repository.NoteRepositoryImpl
import com.example.travelnotes.domain.NoteRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository
}