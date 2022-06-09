package com.task.noteapp.data

import com.task.noteapp.data.source.local.NoteDao
import com.task.noteapp.model.Notes
import javax.inject.Inject

open class NotesRepository @Inject constructor(private val dao:NoteDao) {
    val everNotes = dao.getEveryNotes()

    suspend fun insert(notes: Notes) {
        dao.insert(notes)
    }
    suspend fun update(notes:Notes) {
        dao.update(notes)
    }
    suspend fun  delete (notes:Notes) {
        dao.delete(notes)
    }
}