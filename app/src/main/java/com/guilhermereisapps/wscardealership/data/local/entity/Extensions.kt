package com.guilhermereisapps.wscardealership.data.local.entity

import com.guilhermereisapps.wscardealership.data.model.Car
import com.guilhermereisapps.wscardealership.data.model.Compra
import com.guilhermereisapps.wscardealership.data.model.Comprador

fun Car.toEntity() = CarEntity(
    carId = this.id,
    ano = this.ano,
    combustivel = this.combustivel,
    cor = this.cor,
    modeloId = this.modeloId,
    modeloNome = this.modeloNome,
    numPortas = this.numPortas,
    timestampCadastro = this.timestampCadastro,
    valor = this.valor,
    image = this.image
)

fun CarEntity.toDomain() = Car(
    id = this.carId,
    ano = this.ano,
    combustivel = this.combustivel,
    cor = this.cor,
    modeloId = this.modeloId,
    modeloNome = this.modeloNome,
    numPortas = this.numPortas,
    timestampCadastro = this.timestampCadastro,
    valor = this.valor,
    image = this.image
)

fun Comprador.toEntity() = CompradorEntity(
    nome = this.nome,
    telefone = this.telefone,
    email = this.email
)

fun CompradorEntity.toDomain() = Comprador(
    nome = this.nome,
    telefone = this.telefone,
    email = this.email
)

fun Compra.toEntity() = CompraEntity(
    car = this.car!!.toEntity(),
    comprador = this.comprador!!.toEntity()
)

fun CompraEntity.toDomain() = Compra(
    car = this.car.toDomain(),
    comprador = this.comprador.toDomain()
)
