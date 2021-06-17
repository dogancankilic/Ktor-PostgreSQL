package com.ktorpostgresample.repository

import api.NoteApiImp
import com.ktorpostgresample.model.Note

class NoteRepository(val noteApiImp: NoteApiImp){

     fun getNotes(): ArrayList<Note> {
        return noteApiImp.getNotes()
    }

    fun getNote(id: Int) : Note? {
        return noteApiImp.getNote(id)

    }

    fun addNote(note: Note): Note? {
        return noteApiImp.addNote(note)
    }

    fun deleteNote(id: Int): Boolean {
        return noteApiImp.deleteNote(id)
    }

    fun updateNote(id: Int, note: Note): Note? {
        return noteApiImp.updateNote(id,note)
    }

}