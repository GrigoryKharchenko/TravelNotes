package com.example.travelnotes.presentation.screen.note.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.travelnotes.presentation.screen.note.model.NoteModel

class NoteDiffUtil : DiffUtil.ItemCallback<NoteModel>() {
    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean =
        oldItem == newItem
}