package com.example.noteapp.viewnodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.models.Note
import com.example.noteapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {


    fun addNote(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addNote(note)
        }

    fun deleteNote(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(note)
        }

    fun updateNote(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(note)
        }

    fun getAllNote() : LiveData<List<Note>> = noteRepository.getAllNotes()

}
