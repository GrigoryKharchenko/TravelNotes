package com.example.travelnotes.di.module

import androidx.lifecycle.ViewModel
import com.example.travelnotes.di.ViewModelKey
import com.example.travelnotes.presentation.screen.note.insert.InsertNoteFragment
import com.example.travelnotes.presentation.screen.note.insert.InsertNoteViewModel
import com.example.travelnotes.presentation.screen.note.list.NotesFragment
import com.example.travelnotes.presentation.screen.note.list.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun bindNotesFragment(): NotesFragment

    @ContributesAndroidInjector
    fun bindInsertNoteFragment(): InsertNoteFragment

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    fun bindNotesViewModel(viewModel: NotesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InsertNoteViewModel::class)
    fun bindInsertViewModel(viewModel: InsertNoteViewModel): ViewModel
}