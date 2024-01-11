package br.com.murilo.busca_cep.aplicacao.modelo

import kotlinx.serialization.Serializable

@Serializable
data class EnderecoResponse(
    val cep: String? = null,
    val logradouro: String? = null,
    val complemento: String? = null,
    val bairro: String? = null,
    val localidade: String? = null,
    val uf: String? = null,
)
