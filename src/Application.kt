package com.ktorpostgresample

import com.ktorpostgresample.database.initDB
import com.ktorpostgresample.route.createRoutes
import di.notepadModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import org.koin.ktor.ext.Koin


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(Koin) {
        modules(notepadModule)
    }
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    initDB()

    routing {
        createRoutes()

    }

}

