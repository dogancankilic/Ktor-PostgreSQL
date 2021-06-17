package com.ktorpostgresample.dao


import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Notes : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val title = text("title")
    val note = text("note")
    val status = text("status")
    val priority = text("priority")
}

