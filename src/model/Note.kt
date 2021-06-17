package com.ktorpostgresample.model

import org.jetbrains.exposed.dao.id.EntityID

data class Note (val id: Int, val title: String, val note: String, val status: String, val priority: String){

}