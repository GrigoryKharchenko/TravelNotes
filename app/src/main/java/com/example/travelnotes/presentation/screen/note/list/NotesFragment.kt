package com.example.travelnotes.presentation.screen.note.list

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.travelnotes.R
import com.example.travelnotes.databinding.FragmentNotesBinding
import com.example.travelnotes.presentation.base.BaseFragment
import com.example.travelnotes.presentation.screen.note.insert.InsertNoteFragment
import com.example.travelnotes.presentation.screen.note.list.adapter.NoteAdapter
import com.example.travelnotes.presentation.screen.note.model.NoteModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class NotesFragment : BaseFragment<NotesViewModel>() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private val adapter: NoteAdapter = NoteAdapter(
        onItemClick = { noteModel -> openInsertNote(noteModel) },
        onDeleteClick = { noteModel ->
            showDeleteDialog(noteModel.id)
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fabAddNote.setOnClickListener { navigateToNoteScreen() }
            rvNotes.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listNote.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun navigateToNoteScreen() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                InsertNoteFragment.newInstance(null)
            )
            .addToBackStack(TAG)
            .commit()
    }

    private fun showDeleteDialog(noteId: Int) =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.title_dialog)
            .setMessage(R.string.message_dialog)
            .setNegativeButton(R.string.negative_button_dialog) { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.positive_button_dialog) { dialog: DialogInterface, _: Int ->
                viewModel.deleteNote(noteId)
                dialog.dismiss()
            }
            .show()

    private fun openInsertNote(noteModel: NoteModel) {
        parentFragmentManager.beginTransaction()
            .add(R.id.container, InsertNoteFragment.newInstance(noteModel))
            .addToBackStack("Insert")
            .commit()
    }

    companion object {
        fun newInstance() = NotesFragment()
        const val TAG = "NotesFragment"
    }
}
