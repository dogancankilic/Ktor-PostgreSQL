package api


import com.ktorpostgresample.model.Note

interface NoteApi {

    fun getNotes(): ArrayList<Note>

    fun getNote(id: Int): Note?

    fun addNote(note: Note): Note?

    fun deleteNote(id: Int): Boolean

    fun updateNote(id: Int, note: Note): Note?


}