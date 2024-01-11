package br.com.murilo.busca_cep.ui.stateholder

import androidx.compose.ui.text.AnnotatedString

sealed class ResultadoCepUiState{
    object Carregando: ResultadoCepUiState()
    object Falha: ResultadoCepUiState()
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
