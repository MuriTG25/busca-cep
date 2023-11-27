package br.com.alura.desafio_cep.infraestrutura.modelo

import com.google.gson.annotations.SerializedName

data class EnderecoResponse(
    @SerializedName("cep")
    val cep: String = "",
    @SerializedName("logradouro")
    val logradouro: String = "",
    @SerializedName("bairro")
    val bairro: String = "",
    @SerializedName("localidade")
    val cidade: String = "",
    @SerializedName("uf")
    val estado: String = "",
    @SerializedName("complemento")
    val complemento: String = "",
)