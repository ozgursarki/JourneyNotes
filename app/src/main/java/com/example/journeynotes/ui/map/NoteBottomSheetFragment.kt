package com.example.journeynotes.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.journeynotes.databinding.FragmentBottomSheetBinding
import com.example.journeynotes.domain.model.Note
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NoteBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val note = arguments?.getParcelable<Note>(NOTE)
        binding.title.editText?.setText(note?.title)
        binding.description.editText?.setText(note?.description)
    }

    companion object {
        val TAG = "NoteBottomSheetFragment"
        val NOTE = "ARG_NOTE"
        @JvmStatic
        fun newInstance(note: Note): NoteBottomSheetFragment {
            return NoteBottomSheetFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(NOTE, note)
                arguments = bundle
            }
        }
    }


}