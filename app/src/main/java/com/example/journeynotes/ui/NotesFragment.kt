package com.example.journeynotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.journeynotes.NotesFragmentAdapter
import com.example.journeynotes.R
import com.example.journeynotes.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    private lateinit var binding : FragmentNotesBinding
    private lateinit var placeViewModel: PlaceViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater,container,false)

        val adapter = NotesFragmentAdapter()
        val recyclerView = binding.notesRv
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        placeViewModel = ViewModelProvider(this)[PlaceViewModel::class.java]
        placeViewModel.readAllPlace.observe(viewLifecycleOwner, Observer { place ->
            adapter.setPlaces(place)
        })
        return binding.root
    }

}