package com.guilhermereisapps.wscardealership.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comprador")
data class CompradorEntity(
    @PrimaryKey(autoGenerate = true) val compradorId: Int = 0,
    val nome: String,
    val telefone: String,
    val email: String
)
