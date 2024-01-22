package br.com.murilo.busca_cep.ui.stateholder

import androidx.compose.ui.text.AnnotatedString

sealed class ResultadoCepUiState{
    data object Carregando: ResultadoCepUiState()
    data object Falha: ResultadoCepUiState()

    data object CepInvalido: ResultadoCepUiState()
    data class Sucesso(
        val cep: String = "",
        val logradouro: String = "",
        val bairro: String = "",
        val cidade: String = "",
        val estado: String = "",
        val complemento: String = "",
        val textoParaCopiar: AnnotatedString = AnnotatedString("")
    ):ResultadoCepUiState()
}
