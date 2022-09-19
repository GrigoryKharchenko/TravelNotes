package com.example.travelnotes.presentation.screen.note.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.travelnotes.databinding.ItemNoteBinding
import com.example.travelnotes.presentation.screen.note.model.NoteModel

class NoteAdapter(
    val onItemClick: (NoteModel) -> Unit,
    val onDeleteClick: (NoteModel) -> Unit
) :
    ListAdapter<NoteModel, NoteViewHolder>(NoteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClick = onItemClick, onDeleteItem = onDeleteClick)
}