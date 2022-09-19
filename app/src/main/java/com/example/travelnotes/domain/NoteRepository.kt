package com.example.travelnotes.domain

import com.example.travelnotes.data.entity.NoteEntity
import com.example.travelnotes.presentation.screen.note.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun subscribeToReceive(): Flow<List<NoteEntity>>
    suspend fun insertOrUpdate(noteModel: NoteModel)
    suspend fun delete(noteId:Int)
}