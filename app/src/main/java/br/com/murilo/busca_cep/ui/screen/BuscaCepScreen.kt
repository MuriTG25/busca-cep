package br.com.murilo.busca_cep.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.murilo.busca_cep.ui.stateholder.BuscaCepUiState
import br.com.murilo.busca_cep.ui.theme.amareloPrimario

@Composable
fun BuscaCepScreen(
    modifier: Modifier = Modifier,
    uiState: BuscaCepUiState,
    navegarParaTelaResultado: (String) -> Unit = {},
) {
    Column (modifier = modifier
        .fillMaxSize()
        .background(amareloPrimario)
    ){

    }
}

@Preview(showSystemUi = true)
@Composable
private fun BuscaCepScreenPreview() {
    BuscaCepScreen(
        uiState = BuscaCepUiState()
    )
}