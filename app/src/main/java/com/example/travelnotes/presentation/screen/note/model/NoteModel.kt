package com.example.travelnotes.presentation.screen.note.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    val id: Int,
    val text: String,
    val title:String
):Parcelable
