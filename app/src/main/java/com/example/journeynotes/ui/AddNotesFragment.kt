package com.example.journeynotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.journeynotes.R
import com.example.journeynotes.databinding.FragmentAddNotesBinding

class AddNotesFragment : Fragment() {

    private lateinit var binding : FragmentAddNotesBinding


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
}