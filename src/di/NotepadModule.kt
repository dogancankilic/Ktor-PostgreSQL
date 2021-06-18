package di

import api.NoteApiImp
import com.ktorpostgresample.api.NoteService
import com.ktorpostgresample.repository.NoteRepository
import com.ktorpostgresample.utility.Utility
import org.koin.dsl.module

val notepadModule = module(createdAtStart = true) {

    single { NoteRepository(get())  }

    single { NoteApiImp(get()) }

    single { NoteService() }

    factory { Utility() }
}