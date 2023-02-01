package com.example.journeynotes.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.journeynotes.databinding.FragmentnoteBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NoteBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentnoteBottomsheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentnoteBottomsheetBinding.inflate(inflater,container,false)
        return binding.root
    }
}