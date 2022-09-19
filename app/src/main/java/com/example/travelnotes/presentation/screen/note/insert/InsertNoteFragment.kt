package com.example.travelnotes.presentation.screen.note.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.travelnotes.R
import com.example.travelnotes.databinding.FragmentNoteBinding
import com.example.travelnotes.presentation.base.BaseFragment
import com.example.travelnotes.presentation.screen.note.model.NoteModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class InsertNoteFragment : BaseFragment<InsertNoteViewModel>() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private var noteModel: NoteModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolBar.setNavigationOnClickListener { goBack() }
            checkNote()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewEffect.collect {
                    handleViewEffect(it)
                }
            }
        }
        getArgumentNote()
    }

    private fun handleViewEffect(viewEffect: InsertNoteViewEffect) {
        when (viewEffect) {
            InsertNoteViewEffect.GoBack -> goBack()
            InsertNoteViewEffect.ShowShackbar -> showSnackbar(R.string.error_insert_note)
        }
    }

    private fun showSnackbar(description: Int) {
        Snackbar.make(requireView(), description, Snackbar.LENGTH_SHORT).show()
    }

    private fun goBack() {
        parentFragmentManager.popBackStack()

    }

    private fun setNote(note: NoteModel) {
        binding.run {
            etTitleNote.setText(note.title)
            etTextNote.setText(note.text)
        }
    }

    private fun getArgumentNote() {
        noteModel = arguments?.getParcelable(ARG_NOTE)
        noteModel?.let { setNote(it) }
    }

    private fun checkNote() {
        with(binding) {
            toolBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.check -> {
                        if (noteModel == null) {
                            viewModel.insertOrUpdateNote(
                                noteModel = NoteModel(
                                    0,
                                    title = etTitleNote.text.toString(),
                                    text = etTextNote.text.toString()
                                )
                            )
                        } else {
                            val updateNote = noteModel?.copy(
                                title = etTitleNote.text.toString(),
                                text = etTextNote.text.toString()
                            )
                            updateNote?.let { viewModel.insertOrUpdateNote(it) }
                        }
                        true
                    }
                    else -> false
                }
            }
        }
    }

    companion object {
        fun newInstance(note: NoteModel?) = InsertNoteFragment().apply {
            arguments = bundleOf(ARG_NOTE to note)
        }

        const val ARG_NOTE = "ArgNote"
    }
}