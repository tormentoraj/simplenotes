package com.example.simplenotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplenotes.data.database.Note
import com.example.simplenotes.data.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepository.getRepository(
        com.example.simplenotes.data.database.AppDatabase.getDatabase(application).noteDao()
    )

    val allNotes: LiveData<List<Note>> = repository.allNotes

    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery

    val filteredNotes: LiveData<List<Note>> = _searchQuery.switchMap { query ->
        repository.searchNotes(query)
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun insertNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteNote(note)
        }
    }
}