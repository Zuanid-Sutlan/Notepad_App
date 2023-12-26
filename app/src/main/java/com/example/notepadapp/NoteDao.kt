package com.example.notepadapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("Select * from notes")
    fun getAll() : LiveData<List<Notes>>

    @Insert
    fun insert(notes: Notes)

    @Delete
    fun delete(notes: Notes)

}