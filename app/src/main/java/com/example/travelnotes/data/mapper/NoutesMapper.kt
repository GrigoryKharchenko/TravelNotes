package com.example.travelnotes.data.mapper

import com.example.travelnotes.data.entity.NoteEntity
import com.example.travelnotes.presentation.screen.note.model.NoteModel

fun List<NoteEntity>.toNoteModel() = this.map { noteEntity ->
    noteEntity.toNoteModel()
}

private fun NoteEntity.toNoteModel() =
    NoteModel(
        id = id,
        text = text,
        title = title
    )