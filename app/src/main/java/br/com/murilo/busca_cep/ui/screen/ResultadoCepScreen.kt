package br.com.murilo.busca_cep.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.murilo.busca_cep.ui.component.resultadoCep.TelaDeCarregamentoComponent
import br.com.murilo.busca_cep.ui.component.resultadoCep.TelaDeCepInvalidoComponent
import br.com.murilo.busca_cep.ui.component.resultadoCep.TelaDeFalhaComponent
import br.com.murilo.busca_cep.ui.component.resultadoCep.TelaDeSucessoComponent
import br.com.murilo.busca_cep.ui.stateholder.ResultadoCepUiState

@Composable
fun ResultadoCepScreen(
    modifier: Modifier = Modifier,
    uiState: ResultadoCepUiState,
    aoTentarBuscarNovamenteOEndereco: () -> Unit = {},
    navegarAParaTelaAnterior: () -> Unit = {},
) {
    when (uiState) {
        ResultadoCepUiState.Carregando -> {
            TelaDeCarregamentoComponent()
        }

        ResultadoCepUiState.Falha -> {
            TelaDeFalhaComponent(
                aoTentarBuscarNovamenteOEndereco = aoTentarBuscarNovamenteOEndereco,
                navegarAParaTelaAnterior = navegarAParaTelaAnterior,
            )
        }

        ResultadoCepUiState.CepInvalido -> {
            TelaDeCepInvalidoComponent(
                navegarAParaTelaAnterior = navegarAParaTelaAnterior
            )
        }

        is ResultadoCepUiState.Sucesso -> {
            TelaDeSucessoComponent(
                modifier = modifier,
                uiState = uiState,
                navegarParaATelaAnterior = navegarAParaTelaAnterior
            )
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
}

@Preview(showSystemUi = true)
@Composable
private fun ResultadoCepScreenConcluidoPreview() {
    ResultadoCepScreen(
        uiState = ResultadoCepUiState.Sucesso(
            cep = "00005-345",
            logradouro = "Rua das Palmeiras, 133"
        )
    )
}