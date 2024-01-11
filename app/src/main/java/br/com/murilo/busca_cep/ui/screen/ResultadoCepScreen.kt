package br.com.murilo.busca_cep.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.murilo.busca_cep.ui.component.TelaDeCarregamentoComponent
import br.com.murilo.busca_cep.ui.component.TelaDeFalhaComponent
import br.com.murilo.busca_cep.ui.stateholder.ResultadoCepUiState

@Composable
fun ResultadoCepScreen(
    uiState: ResultadoCepUiState,
    aoTentarBuscarNovamenteOEndereco: () -> Unit = {},
    navegarParaTelaAnterior: () -> Unit = {},
) {
    when(uiState){
        ResultadoCepUiState.Carregando -> {
            TelaDeCarregamentoComponent()
        }
        ResultadoCepUiState.Falha -> {
            TelaDeFalhaComponent(
                aoTentarBuscarNovamenteOEndereco = aoTentarBuscarNovamenteOEndereco,
                voltarTelaAnterior = navegarParaTelaAnterior,
            )
        }
        is ResultadoCepUiState.Sucesso -> {
            SelectionContainer {
                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center){
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = uiState.cep+" - "+uiState.logradouro,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
private fun ResultadoCepScreenCarregandoPreview() {
    ResultadoCepScreen(
        uiState = ResultadoCepUiState.Carregando
    )
}
@Preview(showSystemUi = true)
@Composable
private fun ResultadoCepScreenFalhaPreview() {
    ResultadoCepScreen(
        uiState = ResultadoCepUiState.Falha
    )
}@Preview(showSystemUi = true)
@Composable
private fun ResultadoCepScreenConcluidoPreview() {
    ResultadoCepScreen(
        uiState = ResultadoCepUiState.Sucesso(
            cep = "00005-345",
            logradouro = "Rua das Palmeiras, 133"
        )
    )
}