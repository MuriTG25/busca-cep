package br.com.murilo.busca_cep.ui.stateholder

data class BuscaCepUiState(
    val cep: String = "",
    val alteracaoDoCep: (String) -> Unit = {},
)
