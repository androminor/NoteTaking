package com.task.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.task.noteapp.data.source.local.NotesDatabase
import com.task.noteapp.data.NotesRepository
import com.task.noteapp.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor (application:Application):AndroidViewModel(application) {


    private var _everyNotes = MutableLiveData<List<Notes>>()

    val everyNotes : LiveData<List<Notes>>
        get() = _everyNotes

   val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepository(dao)
        _everyNotes = repository.everNotes.asLiveData() as MutableLiveData<List<Notes>>
    }
    fun updateNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(notes)
    }

    fun addNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notes)
    }
    fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }


}