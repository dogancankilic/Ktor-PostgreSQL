package com.ktorpostgresample

import api.NoteApi
import api.NoteApiImp
import com.ktorpostgresample.entity.Notes
import com.ktorpostgresample.model.Note
import com.ktorpostgresample.repository.NoteRepository
import di.notepadModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.context.startKoin
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
import org.koin.ktor.ext.getKoin

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {


    install(Koin) {
        //slf4jLogger()
        modules(notepadModule)
    }
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    Database.connect("jdbc:postgresql://localhost:5432/", driver = "org.postgresql.Driver",
        user = "postgres", password = "password")
    transaction {
        SchemaUtils.create(Notes)
    }

    routing {

        val repository=get<NoteRepository>()

        get("/notes") {
            call.respond(repository.getNotes())
        }

        get ("/notes/{id}"){
            val note = call.parameters["id"]?.let { it -> repository.getNote(it.toInt()) }
            if (note == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(note)

        }

        post("/notes/add") {
            val noteDto = call.receive<Note>()
            if (noteDto.note==null) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            repository.addNote(noteDto)
            call.respond(HttpStatusCode.OK)

        }

        post("/notes/update/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val noteDto = call.receive<Note>()
            repository.updateNote(id,noteDto)
            call.respond(noteDto)
        }

        post("/notes/delete/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val removed = repository.deleteNote(id)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }



    }


}

