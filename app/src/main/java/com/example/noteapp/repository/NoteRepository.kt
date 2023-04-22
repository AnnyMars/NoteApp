package com.example.noteapp.repository

import com.example.noteapp.db.NoteDatabase
import com.example.noteapp.models.Note

class NoteRepository(private val db: NoteDatabase) {

    suspend fun addNote(note: Note) = db.getNoteDao().addNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)
    fun getAllNotes() = db.getNoteDao().getAll()
}