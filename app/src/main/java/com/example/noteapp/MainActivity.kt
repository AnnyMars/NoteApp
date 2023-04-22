package com.example.noteapp

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.db.NoteDatabase
import com.example.noteapp.fragments.HomeFragment
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.viewnodel.NoteViewModel
import com.example.noteapp.viewnodel.NoteViewModelFactory



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)



        val noteRepository = NoteRepository(NoteDatabase(this))
        val factory = NoteViewModelFactory(noteRepository)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }


}