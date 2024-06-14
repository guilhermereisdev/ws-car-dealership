package com.guilhermereisapps.wscardealership.data.model

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Car(
    @SerializedName("ano")
    val ano: Int? = null,
    @SerializedName("combustivel")
    val combustivel: String? = null,
    @SerializedName("cor")
    val cor: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("modelo_id")
    val modeloId: Int? = null,
    @SerializedName("nome_modelo")
    val modeloNome: String? = null,
    @SerializedName("num_portas")
    val numPortas: Int? = null,
    @SerializedName("timestamp_cadastro")
    val timestampCadastro: Long? = null,
    @SerializedName("valor")
    val valor: Double? = null,
    @SerializedName("image")
    @DrawableRes var image: Int? = null,
)
