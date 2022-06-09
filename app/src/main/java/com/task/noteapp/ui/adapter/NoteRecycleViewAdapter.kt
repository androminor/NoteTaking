package com.task.noteapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.R
import com.task.noteapp.databinding.NoteItemsBinding
import com.task.noteapp.model.Notes
import com.task.noteapp.utility.showShortSnack

class NoteRecycleViewAdapter(
    val context: Context,
    val clickInterface: clickListener,
    val deletedInterface: deleteListener
) : RecyclerView.Adapter<NoteRecycleViewAdapter.ViewHolder>() {
    private val everyNote = ArrayList<Notes>()

    inner class ViewHolder(val binding: NoteItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.idTitle
        val time: TextView = binding.idTime
        val desc: TextView = binding.idDesc
        val deleteButton: ImageView = binding.idDelete
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteRecycleViewAdapter.ViewHolder {
        val binding = NoteItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NoteRecycleViewAdapter.ViewHolder, position: Int) {
        val itemsView = everyNote[position]

        with(holder)
        {
            title.setText(itemsView.noteTitle)
            desc.setText(itemsView.noteDescription)
            time.setText("Edited   "+  itemsView.timeStamp)

            deleteButton.setOnClickListener {
                deletedInterface.onDeleteClicking(itemsView)

                binding.idDelete.showShortSnack("Deleted")

            }

            binding.root.setOnClickListener {
                clickInterface.onNoteClicking(itemsView)

            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNotes(noteList: List<Notes>) {
        everyNote.clear()
        everyNote.addAll(noteList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return everyNote.size
    }

    interface clickListener {
        fun onNoteClicking(note: Notes)
    }

    interface deleteListener {
        fun onDeleteClicking(note: Notes)
    }
}