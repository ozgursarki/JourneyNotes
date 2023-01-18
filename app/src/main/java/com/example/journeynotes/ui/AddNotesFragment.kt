package com.example.journeynotes.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.journeynotes.R
import com.example.journeynotes.databinding.FragmentAddNotesBinding
import com.example.journeynotes.model.Place
import com.example.journeynotes.roomdb.PlaceDao
import com.example.journeynotes.roomdb.PlaceDatabase
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener

class AddNotesFragment : Fragment() {

    private lateinit var binding : FragmentAddNotesBinding
    private lateinit var placeViewModel: PlaceViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNotesBinding.inflate(inflater,container,false)

        placeViewModel = ViewModelProvider(this)[PlaceViewModel::class.java]
        binding.floatingActionButton.setOnClickListener {
            insertDatatoDatabase()
        }

        return binding.root
    }

    private fun insertDatatoDatabase() {
        val name = binding.title.prefixTextView.toString()
        val description =binding.description.prefixTextView.toString()

        if (inputCheck(name, description)) {
            val place = Place(name,description)
            placeViewModel.addPlace(place)
            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addNotesFragment_to_notesFragment2)
        }else {
            Toast.makeText(requireContext(),"Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String,description:String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(description))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}