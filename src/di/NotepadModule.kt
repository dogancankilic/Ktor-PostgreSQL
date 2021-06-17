package di

import api.NoteApiImp
import com.ktorpostgresample.repository.NoteRepository
import org.koin.dsl.module

val notepadModule = module(createdAtStart = true) {

    single { NoteRepository(get())  }

    single { NoteApiImp() }
}