package com.guilhermereisapps.wscardealership.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.guilhermereisapps.wscardealership.data.local.entity.CompraEntity

@Dao
interface CompraDao {
    @Insert
    suspend fun insertCompra(compra: CompraEntity)

    @Query("SELECT * FROM compra")
    suspend fun getAllCompras(): List<CompraEntity>
}
