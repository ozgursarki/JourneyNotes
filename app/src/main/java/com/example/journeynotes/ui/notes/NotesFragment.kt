package com.example.journeynotes.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.journeynotes.ui.adapter.NotesFragmentAdapter
import com.example.journeynotes.databinding.FragmentNotesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var adapter: NotesFragmentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.notes.collect {
                        handleNotesUIState(it)
                    }
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NotesFragmentAdapter(deleteNoteCallBack = {
            viewModel.deleteNoteFromDatabase(it)
            //viewModel.getNotesFromDatabase()
        }, callBackNoteClick = {
            val action = NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(it)
            findNavController().navigate(action)
        })
        binding.artan.setOnClickListener {
            viewModel.sortNote(true)
        }
        binding.azalan.setOnClickListener {
            viewModel.sortNote(false)
        }
        val recyclerView = binding.notesRv
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)


    }

    private fun handleNotesUIState(noteScreenUiState: NoteScreenUiState) {
        adapter.setNotesList(noteScreenUiState.noteList)
    }
}