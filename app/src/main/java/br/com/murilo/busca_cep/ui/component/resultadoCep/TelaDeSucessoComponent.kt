package br.com.murilo.busca_cep.ui.component.resultadoCep

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import br.com.murilo.busca_cep.aplicacao.extras.naoEhVazioOuNulo
import br.com.murilo.busca_cep.ui.component.comum.BotaoComponent
import br.com.murilo.busca_cep.ui.extras.enderecoDeTeste
import br.com.murilo.busca_cep.ui.extras.margemPadrao
import br.com.murilo.busca_cep.ui.extras.mensagemDeAviso
import br.com.murilo.busca_cep.ui.stateholder.ResultadoCepUiState

@Composable
fun TelaDeSucessoComponent(
    modifier: Modifier = Modifier,
    uiState: ResultadoCepUiState.Sucesso,
    clipboardManager: ClipboardManager = LocalClipboardManager.current,
    context: Context = LocalContext.current,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(margemPadrao)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(margemPadrao),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        ColunaResultadoComponent(
            existeElemento = uiState.cep.naoEhVazioOuNulo(),
            textoDaDescricao = "CEP",
            textoDoCampo = uiState.cep,
        )
        ColunaResultadoComponent(
            existeElemento = uiState.logradouro.naoEhVazioOuNulo(),
            textoDaDescricao = "Logradouro",
            textoDoCampo = uiState.logradouro
        )
        ColunaResultadoComponent(
            existeElemento = uiState.complemento.naoEhVazioOuNulo(),
            textoDaDescricao = "Complemento",
            textoDoCampo = uiState.complemento,
        )
        ColunaResultadoComponent(
            existeElemento = uiState.bairro.naoEhVazioOuNulo(),
            textoDaDescricao = "Bairro",
            textoDoCampo = uiState.bairro
        )
        ColunaResultadoComponent(
            existeElemento = uiState.cidade.naoEhVazioOuNulo(),
            textoDaDescricao = "Cidade",
            textoDoCampo = uiState.cidade
        )
        ColunaResultadoComponent(
            existeElemento = uiState.estado.naoEhVazioOuNulo(),
            textoDaDescricao = "Estado",
            textoDoCampo = uiState.estado
        )
        BotaoComponent(
            texto = "Copiar tudo",
            noClicarBotao = {
                clipboardManager.setText(
                    uiState.textoParaCopiar
                )
                context.mensagemDeAviso("Texto copiado para o clipboard")
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaDeSucessoComponentPreview() {
    with(enderecoDeTeste){
        TelaDeSucessoComponent(
            uiState = ResultadoCepUiState.Sucesso(
                cep = cep,
                logradouro = logradouro,
                bairro = bairro,
                cidade = cidade,
                estado = estado,
                complemento = complemento
            ))
    }
}