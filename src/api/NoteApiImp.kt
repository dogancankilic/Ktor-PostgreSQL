package api


import com.ktorpostgresample.dao.Notes
import com.ktorpostgresample.model.Note
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class NoteApiImp : NoteApi {

    override fun getNotes(): ArrayList<Note> {
        val notes: ArrayList<Note> = arrayListOf()

        transaction {
            Notes.selectAll().map {
                notes.add(
                    Note(
                        id =it[Notes.id],
                        title = it[Notes.title],
                        note = it[Notes.note],
                        status = it[Notes.status],
                        priority = it[Notes.priority]

                    )
                )
            }
        }
        return notes
    }

    override fun getNote(id: Int): Note?  {
        return transaction {
            Notes.select { Notes.id eq id }
                .map { Note(id = it[Notes.id],title= it[Notes.title],note = it[Notes.note],status = it[Notes.status],priority = it[Notes.priority]) }
                .first()
        }

    }

    override fun addNote(note: Note): Note? {
        transaction {
            Notes.insert {
                it[title] = note.title
                it[this.note] = note.note
                it[status] = note.status
                it[priority] = note.priority
            }
        }
        return Note(note.id,note.title, note.note, note.status,note.priority)
    }

    override fun deleteNote(id: Int): Boolean {
        return transaction {
         Notes.deleteWhere { Notes.id eq id } > 0
        }

    }

    override fun updateNote(id: Int, note: Note): Note? {
        Notes.update({ Notes.id eq id}) {
            it[title] = note.title
            it[this.note] = note.note
            it[status] = note.status
            it[priority] = note.priority
        }
        return  Note(note.id,note.title, note.note, note.status,note.priority)
    }
}

