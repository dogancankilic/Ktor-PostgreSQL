package com.ktorpostgresample.database

import com.ktorpostgresample.dao.Notes
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


fun Application.initDB() {
    val config = HikariConfig("/hikaricp.properties")
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)
    transaction {
        SchemaUtils.create(
            Notes
        )

    }
}





