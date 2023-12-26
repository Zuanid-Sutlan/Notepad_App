package com.example.notepadapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: Repository
    private val allNotes: LiveData<List<Notes>>
    init {
        val dao = MyDataBase.getDataBase(application).noteDao()
        repository = Repository(dao)
        allNotes = repository.getAllNote()
    }

    // get notes data form room database
    fun getAll(): LiveData<List<Notes>> {
        return repository.getAllNote()
    }

    // fun to insert data in room database
    fun insert(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(notes)
        }
    }

    // fun to delete data in room database
    fun delete(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(notes)
        }
    }



}