package com.example.travelnotes.data.repository

import com.example.travelnotes.data.dao.NoteDao
import com.example.travelnotes.data.entity.NoteEntity
import com.example.travelnotes.domain.NoteRepository
import com.example.travelnotes.presentation.screen.note.model.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun subscribeToReceive(): Flow<List<NoteEntity>> = noteDao.subscribeToReceive()

    override suspend fun insertOrUpdate(noteModel: NoteModel) {
        noteDao.insertOrUpdate(
            NoteEntity(
                text = noteModel.text,
                title = noteModel.title,
                id = noteModel.id
            )
        )
    }

    override suspend fun delete(noteId: Int) {
        noteDao.delete(noteId)
    }
}