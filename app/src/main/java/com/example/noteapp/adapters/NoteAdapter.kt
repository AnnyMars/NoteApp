package com.example.noteapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteItemBinding
import com.example.noteapp.fragments.HomeFragment
import com.example.noteapp.fragments.HomeFragmentDirections
import com.example.noteapp.models.Note


class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var binding: NoteItemBinding? = null

    class NoteViewHolder(itemBinding: NoteItemBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback =
        object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    var differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding!!)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val curNote = differ.currentList[position]
        holder.itemView.apply {
            binding?.noteTitle?.text = curNote.title
            binding?.noteDesc?.text = curNote.desc
        }.setOnClickListener { mView ->
            val direction = HomeFragmentDirections
                .actionHomeFragmentToUpdateNoteFragment(curNote)
            mView.findNavController().navigate(direction)
        }
    }
}