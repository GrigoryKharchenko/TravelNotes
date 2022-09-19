package com.example.travelnotes.data.database

import com.example.travelnotes.data.dao.NoteDao

interface AppDatabase {

    fun noteDao(): NoteDao
}