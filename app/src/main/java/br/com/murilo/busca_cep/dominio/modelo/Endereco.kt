package br.com.murilo.busca_cep.dominio.modelo

data class Endereco(
    val cep: String,
    val logradouro: String,
    val complemento: String? = null,
    val bairro: String,
    val localidade: String,
    val uf: String,
)
