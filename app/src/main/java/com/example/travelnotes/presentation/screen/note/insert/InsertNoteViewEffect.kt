package com.example.travelnotes.presentation.screen.note.insert

sealed class InsertNoteViewEffect {
    object ShowShackbar : InsertNoteViewEffect()
    object GoBack : InsertNoteViewEffect()
}