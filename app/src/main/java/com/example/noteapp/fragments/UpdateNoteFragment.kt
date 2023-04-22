package com.example.noteapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentUpdateNoteBinding
import com.example.noteapp.models.Note
import com.example.noteapp.utils.toast
import com.example.noteapp.viewnodel.NoteViewModel

class UpdateNoteFragment : Fragment() {

    private lateinit var binding: FragmentUpdateNoteBinding
    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var curNote: Note
    private lateinit var noteViewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel

        curNote = args.note!!
        binding.updateNoteTitle.setText(curNote.title)
        binding.updateNoteDesc.setText(curNote.desc)

        binding.fabDone.setOnClickListener{
            val title = binding.updateNoteTitle.text.toString().trim()
            val desc = binding.updateNoteDesc.text.toString().trim()

            if(title.isNotEmpty()){
                val note = Note(curNote.id, title, desc)
                noteViewModel.updateNote(note)

                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            } else{
                activity?.toast("Enter title")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_menu -> {
                noteViewModel.deleteNote(curNote)
                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }




}