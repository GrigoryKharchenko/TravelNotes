package com.example.travelnotes.presentation.screen.note.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelnotes.domain.NoteRepository
import com.example.travelnotes.presentation.screen.note.model.NoteModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InsertNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _viewEffect = MutableSharedFlow<InsertNoteViewEffect>()
    val viewEffect get() = _viewEffect.asSharedFlow()

    fun insertOrUpdateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            if (noteModel.text.isEmpty() || noteModel.title.isEmpty()  ) {
                _viewEffect.emit(InsertNoteViewEffect.ShowShackbar)
            } else {
                noteRepository.insertOrUpdate(noteModel)
                _viewEffect.emit(InsertNoteViewEffect.GoBack)
            }
        }
    }
}