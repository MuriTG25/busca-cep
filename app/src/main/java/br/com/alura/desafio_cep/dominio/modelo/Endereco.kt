package br.com.alura.desafio_cep.dominio.modelo

data class Endereco(
    val cep: String = "",
    val logradouro: String = "",
    val numero: String = "",
    val bairro: String = "",
    val cidade: String = "",
    val estado: String = "",
    val complemento: String = "",
)
