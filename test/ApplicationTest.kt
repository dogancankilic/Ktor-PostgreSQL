package test

import com.google.gson.Gson
import com.ktorpostgresample.database.initDB
import com.ktorpostgresample.route.notepadRoutes
import di.notepadModule
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import org.koin.test.KoinTest
import kotlin.test.assertEquals
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import org.junit.Rule
import org.koin.test.KoinTestRule
import test.model.NoteData
class ApplicationTest : KoinTest {

    /* Testing routes on local database */


    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(notepadModule)
    }

    @Test
    fun `check if add note endpoint status ok`() = withTestApplication(


        {
            initDB()
            install(ContentNegotiation){ gson () }
            routing { notepadRoutes() }

        }
    ) {


        val c = handleRequest(HttpMethod.Post, "/notes/add") {
            var j = Gson().toJson(NoteData("Title","Note","active","high"))
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(j)

        }
        assertEquals(HttpStatusCode.OK, c.response.status())
    }


    @Test
    fun `check if delete note endpoint status ok`() = withTestApplication(


        {
            initDB()
            install(ContentNegotiation){ gson () }
            routing { notepadRoutes() }

        }
    ) {


        val c = handleRequest(HttpMethod.Post, "/notes/delete/1") {

        }
        assertEquals(HttpStatusCode.OK, c.response.status())
    }

    @Test
    fun `check if update note endpoint status ok`() = withTestApplication(


        {
            initDB()
            install(ContentNegotiation){ gson () }
            routing { notepadRoutes() }

        }
    ) {


        val c = handleRequest(HttpMethod.Post, "/notes/update/1") {
            var j = Gson().toJson(NoteData("Title","Note","active","high"))
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(j)

        }
        assertEquals(HttpStatusCode.OK, c.response.status())
    }

    @Test
    fun `check if get note endpoint status ok`() = withTestApplication(


        {
            initDB()
            install(ContentNegotiation){ gson () }
            routing { notepadRoutes() }

        }
    ) {


        val c = handleRequest(HttpMethod.Get, "/notes/1") {
        }
        assertEquals(HttpStatusCode.OK, c.response.status())
    }

    @Test
    fun `check if get notes endpoint status ok`() = withTestApplication(


        {
            initDB()
            install(ContentNegotiation){ gson () }
            routing { notepadRoutes() }

        }
    ) {


        val c = handleRequest(HttpMethod.Get, "/notes") {
        }
        assertEquals(HttpStatusCode.OK, c.response.status())
    }



}
