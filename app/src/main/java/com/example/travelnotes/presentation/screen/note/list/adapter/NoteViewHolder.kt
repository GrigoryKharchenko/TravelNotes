package com.example.travelnotes.presentation.screen.note.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.travelnotes.databinding.ItemNoteBinding
import com.example.travelnotes.presentation.screen.note.model.NoteModel

class NoteViewHolder(private val binding: ItemNoteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        noteModel: NoteModel,
        onItemClick: (NoteModel) -> Unit,
        onDeleteItem: (NoteModel) -> Unit
    ) {
        with(binding) {
            tvTextNote.text = noteModel.text
            tvTitleNote.text = noteModel.title
            iBtnDeleteNote.setOnClickListener { onDeleteItem(noteModel) }
            root.setOnClickListener {
                if (RecyclerView.NO_POSITION != adapterPosition) {
                    onItemClick(noteModel)
                }
            }
        }
    }
}
