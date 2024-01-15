package br.com.murilo.busca_cep.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.murilo.busca_cep.ui.component.comum.BotaoComponent
import br.com.murilo.busca_cep.ui.component.resultadoCep.TelaDeCarregamentoComponent
import br.com.murilo.busca_cep.ui.component.resultadoCep.TelaDeCepInvalidoComponent
import br.com.murilo.busca_cep.ui.component.resultadoCep.TelaDeFalhaComponent
import br.com.murilo.busca_cep.ui.component.comum.TextoCopiavelComponent
import br.com.murilo.busca_cep.ui.component.resultadoCep.TelaDeSucessoComponent
import br.com.murilo.busca_cep.ui.extras.margemPadrao
import br.com.murilo.busca_cep.ui.extras.mensagemDeAviso
import br.com.murilo.busca_cep.ui.stateholder.ResultadoCepUiState

@Composable
fun ResultadoCepScreen(
    modifier: Modifier = Modifier,
    uiState: ResultadoCepUiState,
    aoTentarBuscarNovamenteOEndereco: () -> Unit = {},
    navegarParaTelaAnterior: () -> Unit = {},
) {
    val context: Context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
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
        ResultadoCepUiState.CepInvalido -> {
            TelaDeCepInvalidoComponent(
                voltarTelaAnterior = navegarParaTelaAnterior
            )
        }
        is ResultadoCepUiState.Sucesso -> {
            TelaDeSucessoComponent(
                modifier = modifier,
                uiState = uiState,
                clipboardManager = clipboardManager,
                context = context
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