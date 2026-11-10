package com.example.simplenotes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.simplenotes.data.database.AppDatabase
import com.example.simplenotes.data.database.Note
import com.example.simplenotes.data.database.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun searchNotes(query: String): LiveData<List<Note>> {
        return if (query.isBlank()) {
            allNotes
        } else {
            noteDao.searchNotes(query)
        }
    }

    fun getNoteById(id: Long): LiveData<Note> = noteDao.getNoteById(id)

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    companion object {
        @Volatile
        private var INSTANCE: NoteRepository? = null

        fun getRepository(noteDao: NoteDao): NoteRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = NoteRepository(noteDao)
                INSTANCE = instance
                instance
            }
        }
    }
}