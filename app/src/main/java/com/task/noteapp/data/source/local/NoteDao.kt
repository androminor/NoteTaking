package com.task.noteapp.data.source.local

import androidx.room.*
import com.task.noteapp.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Update
    suspend fun update(notes:Notes)

    @Delete
    suspend fun delete(notes:Notes)

    @Query("Select * from noteTable order by id DESC")
    fun getEveryNotes(): Flow<List<Notes>>
}