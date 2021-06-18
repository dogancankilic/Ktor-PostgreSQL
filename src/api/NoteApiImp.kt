package api


import com.ktorpostgresample.api.NoteService
import com.ktorpostgresample.model.Note


class NoteApiImp(val noteService: NoteService) : NoteApi {

    override fun getNotes(): ArrayList<Note> =noteService.getNotes()

    override fun getNote(id: Int)=noteService.getNote(id)

    override fun addNote(note: Note)=noteService.addNote(note)

    override fun deleteNote(id: Int)=noteService.deleteNote(id)

    override fun updateNote(id: Int, note: Note)=noteService.updateNote(id, note)
}

