package com.example.journeynotes.ui.addnotes

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.journeynotes.R
import com.example.journeynotes.databinding.FragmentAddNotesBinding
import com.example.journeynotes.domain.model.Note
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class AddNotesFragment : Fragment() {

    private lateinit var binding : FragmentAddNotesBinding
    private val addNotesViewModel: AddNotesViewModel by viewModels()
    private val args: AddNotesFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNotesBinding.inflate(inflater,container,false)



        return binding.root
    }





    private fun inputCheck(name: String,description:String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(description))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val location = args.location

        binding.floatingActionButton.setOnClickListener {
            val title = binding.title.editText?.text.toString()
            val description = binding.title.editText?.text.toString()
            if (inputCheck(title,description) ) {
                val note = Note(
                    title = title,
                    description = description,
                    location = location,
                    timeStamp = System.currentTimeMillis(),
                )
                addNotesViewModel.insertDatatoDatabase(note)
                findNavController().navigate(R.id.action_addNotesFragment_to_notesFragment)

            }
        }
    }

}