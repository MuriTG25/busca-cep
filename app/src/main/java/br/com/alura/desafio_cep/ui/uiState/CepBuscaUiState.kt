package br.com.alura.desafio_cep.ui.uiState

data class CepBuscaUiState(
    val cep: String = "",
    val logradouro: String = "",
    val numero: String = "",
    val bairro: String = "",
    val cidade: String = "",
    val estado: String = "",
    val complemento: String = "",
    val naMudancaDeCep: (String) -> Unit = {},
    val naMudancaDeLogradouro: (String) -> Unit = {},
    val naMudancaDeNumero: (String) -> Unit = {},
    val naMudancaDeBairro: (String) -> Unit = {},
    val naMudancaDeCidade: (String) -> Unit = {},
    val naMudancaDeEstado: (String) -> Unit = {},
    val naMudancaDeComplemento: (String) -> Unit = {},
)
