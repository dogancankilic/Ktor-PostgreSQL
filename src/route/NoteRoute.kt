package com.ktorpostgresample.route

import com.ktorpostgresample.model.Note
import com.ktorpostgresample.repository.NoteRepository


import com.ktorpostgresample.utility.Utility
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.get


fun Route.notepadRoutes() {
    val repository = get<NoteRepository>()
    val utility = get<Utility>()


    get("/notes") {
        call.respond(repository.getNotes())
    }

    get("/notes/{id}") {
        val id = call.parameters["id"]
        if (id?.let { it1 -> utility.checkNumber(it1) } == false) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        val note = call.parameters["id"]?.let { it -> repository.getNote(it.toInt()) }
        if (note == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(note)

    }

    post("/notes/add") {
        val noteDto = call.receive<Note>()
        if (noteDto.note == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        repository.addNote(noteDto)
        call.respond(HttpStatusCode.OK)

    }

    post("/notes/update/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val noteDto = call.receive<Note>()
        if (noteDto.note == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        repository.updateNote(id, noteDto)
        call.respond(noteDto)
    }

    post("/notes/delete/{id}") {
        val id = call.parameters["id"]
        if (id?.let { it1 -> utility.checkNumber(it1) } == false) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        val removed = id?.let { it1 -> repository.deleteNote(it1.toInt()) }
        if (removed == true) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }


}
fun Application.createRoutes() {
    routing {
        notepadRoutes()
    }
}
