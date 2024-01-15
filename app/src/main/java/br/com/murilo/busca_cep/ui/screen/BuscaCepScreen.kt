package br.com.murilo.busca_cep.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.murilo.busca_cep.R
import br.com.murilo.busca_cep.ui.component.buscaCep.DialogErroDigitoComponent
import br.com.murilo.busca_cep.ui.component.buscaCep.ImagemComponent
import br.com.murilo.busca_cep.ui.component.comum.BotaoComponent
import br.com.murilo.busca_cep.ui.component.comum.CampoDeTextoComponent
import br.com.murilo.busca_cep.ui.component.comum.TextoComponent
import br.com.murilo.busca_cep.ui.extras.TransformadorDeCep
import br.com.murilo.busca_cep.ui.extras.margemPadrao
import br.com.murilo.busca_cep.ui.extras.tamanhoFonteMini
import br.com.murilo.busca_cep.ui.stateholder.BuscaCepUiState
import br.com.murilo.busca_cep.ui.theme.amareloSecundario
import br.com.murilo.busca_cep.ui.theme.azulPrimario

@Composable
fun BuscaCepScreen(
    modifier: Modifier = Modifier,
    uiState: BuscaCepUiState,
    navegarParaTelaResultado: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(amareloSecundario)
            .padding(margemPadrao),
        verticalArrangement = Arrangement.spacedBy(margemPadrao)
    ) {
        ImagemComponent()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(amareloSecundario)
                .wrapContentHeight(Alignment.CenterVertically)
                .offset(0.dp, (-100).dp),
            verticalArrangement = Arrangement.spacedBy(margemPadrao)
        ) {
            if(uiState.mensagemCampoVazio){
                TextoComponent(
                    texto = "O campo é obrigatório",
                    color = Color.Red,
                    fontSize = tamanhoFonteMini,
                    fontWeight = FontWeight.Bold
                )
            }
            CampoDeTextoComponent(
                modifier = Modifier.fillMaxWidth(),
                nomeDaLabel = "CEP",
                dicaDoCampo = "Digite o CEP (8 dígitos, sem o -)",
                valor = uiState.cep,
                naMudancaDeValor = uiState.alteracaoDoCep,
                tipoDeTeclado = KeyboardType.NumberPassword,
                transformacaoVisual = TransformadorDeCep()
            )
            BotaoComponent(
                modifier = Modifier.fillMaxWidth(),
                texto = "Buscar Endereço",
                noClicarBotao = navegarParaTelaResultado,
                corDoBotao = azulPrimario
            )
        }
        if(uiState.mensagemCepMenos8Digitos){
            DialogErroDigitoComponent(
                fecharDialog = {
                    uiState.naAlteracaoDaMensagemCepMenos8Digitos(false)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun BuscaCepScreenPreview() {
    BuscaCepScreen(
        uiState = BuscaCepUiState()
    )
}