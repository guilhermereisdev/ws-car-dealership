package com.guilhermereisapps.wscardealership.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guilhermereisapps.wscardealership.data.local.dao.CompraDao
import com.guilhermereisapps.wscardealership.data.local.entity.CarEntity
import com.guilhermereisapps.wscardealership.data.local.entity.CompraEntity
import com.guilhermereisapps.wscardealership.data.local.entity.CompradorEntity

@Database(entities = [CarEntity::class, CompradorEntity::class, CompraEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun compraDao(): CompraDao
}
