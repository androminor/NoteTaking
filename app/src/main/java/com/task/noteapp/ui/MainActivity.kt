package com.task.noteapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.noteapp.databinding.ActivityMainBinding
import com.task.noteapp.model.Notes
import com.task.noteapp.ui.adapter.NoteRecycleViewAdapter
import com.task.noteapp.utility.viewBinding
import com.task.noteapp.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

/*Using viewbinding delegate in MainActivity*/
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NoteRecycleViewAdapter.clickListener,
    NoteRecycleViewAdapter.deleteListener {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //by view binding delegate you can remove setContentView()
        binding.idNotes.layoutManager = LinearLayoutManager(this)

        val noteAdapter = NoteRecycleViewAdapter(this, this, this)
        binding.idNotes.adapter = noteAdapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)

        viewModel.everyNotes.observe(this) { List ->
            List?.let {
                noteAdapter.updateNotes(it) }
        }

        binding.idAddNote.setOnClickListener {
        val intent = Intent(this@MainActivity,EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNoteClicking(note: Notes) {
        val intent = Intent(this@MainActivity,EditNoteActivity::class.java)
        intent.putExtra("type","Edit")
        intent.putExtra("title",note.noteTitle)
        intent.putExtra("description",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
    }

    override fun onDeleteClicking(note: Notes) {
        viewModel.deleteNote(note)

    }
}