package com.guilhermereisapps.wscardealership.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded

@Entity(tableName = "compra")
data class CompraEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val car: CarEntity,
    @Embedded val comprador: CompradorEntity
)