package com.guilhermereisapps.wscardealership.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class CarEntity(
    @PrimaryKey val carId: Int? = null,
    val ano: Int? = null,
    val combustivel: String? = null,
    val cor: String? = null,
    val modeloId: Int? = null,
    val modeloNome: String? = null,
    val numPortas: Int? = null,
    val timestampCadastro: Long? = null,
    val valor: Double? = null,
    val image: Int? = null,
)
