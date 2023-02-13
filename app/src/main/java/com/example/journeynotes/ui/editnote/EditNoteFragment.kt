package com.example.journeynotes.ui.editnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.journeynotes.R
import com.example.journeynotes.databinding.FragmentAddNotesBinding
import com.example.journeynotes.databinding.FragmentEditNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditNoteFragment : Fragment() {
    private lateinit var binding: FragmentEditNoteBinding
    private val editNoteViewModel : EditNoteViewModel by viewModels()
    val args: EditNoteFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNoteBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val note = args.note
        binding.title.editText?.setText(note.title)
        binding.description.editText?.setText(note.description)
            binding.floatingActionButton.setOnClickListener {
            val title = binding.title.editText?.text.toString()
            val description = binding.description.editText?.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty() && note.id != null) {
                editNoteViewModel.uptadeNote(title,description,note.id!!, navigateToNotesFragment = {
                    //TODO("This code snippet will be change")
                    requireActivity().onBackPressed()
                })


            }
        }

    }

}