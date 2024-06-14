package com.guilhermereisapps.wscardealership.domain.repository

import com.guilhermereisapps.wscardealership.data.model.Compra

interface CompraRepository {
    suspend fun insertCompra(compra: Compra)
    suspend fun getAllCompras(): List<Compra>
}
