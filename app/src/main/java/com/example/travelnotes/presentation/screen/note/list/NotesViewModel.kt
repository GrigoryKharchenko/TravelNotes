package com.example.travelnotes.presentation.screen.note.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelnotes.data.mapper.toNoteModel
import com.example.travelnotes.domain.NoteRepository
import com.example.travelnotes.presentation.screen.note.model.NoteModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _listNote = MutableStateFlow<List<NoteModel>>(emptyList())
    val listNote = _listNote.asStateFlow()

    init {
        subscribeToGetNotes()
    }

    private fun subscribeToGetNotes() {
        noteRepository.subscribeToReceive()
            .map { listNoteEntity ->
                listNoteEntity.toNoteModel()
            }
            .onEach { listNoteModel ->
                _listNote.emit(listNoteModel)
            }
            .launchIn(viewModelScope)
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            noteRepository.delete(noteId)
        }
    }
}