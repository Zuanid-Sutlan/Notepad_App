package com.example.notepadapp

import androidx.lifecycle.LiveData

class Repository(private val noteDao: NoteDao) {

    fun getAllNote() : LiveData<List<Notes>> {
        return noteDao.getAll()
    }

    fun insertNote(notes: Notes){
        noteDao.insert(notes)
    }

    fun deleteNote(notes: Notes){
        noteDao.delete(notes)
    }

}