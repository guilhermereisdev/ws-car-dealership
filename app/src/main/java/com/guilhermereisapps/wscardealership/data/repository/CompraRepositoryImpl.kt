package com.guilhermereisapps.wscardealership.data.repository

import com.guilhermereisapps.wscardealership.data.local.dao.CompraDao
import com.guilhermereisapps.wscardealership.data.local.entity.toDomain
import com.guilhermereisapps.wscardealership.data.local.entity.toEntity
import com.guilhermereisapps.wscardealership.data.model.Compra
import com.guilhermereisapps.wscardealership.domain.repository.CompraRepository

class CompraRepositoryImpl(private val compraDao: CompraDao) : CompraRepository {
    override suspend fun insertCompra(compra: Compra) {
        compraDao.insertCompra(compra.toEntity())
    }

    override suspend fun getAllCompras(): List<Compra> {
        return compraDao.getAllCompras().map { it.toDomain() }
    }
}
