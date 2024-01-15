package br.com.murilo.busca_cep.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.murilo.busca_cep.ui.extras.margemPadrao
import br.com.murilo.busca_cep.ui.extras.tamanhoFonteGrande

@Composable
fun TelaDeCepInvalidoComponent(
    voltarTelaAnterior: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(horizontal = margemPadrao),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextoComponent(
            modifier = Modifier
                .fillMaxWidth()
            ,
            texto = "O valor do CEP é inválido",
            fontSize = tamanhoFonteGrande,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

            )
        BotaoComponent(
            texto = "Voltar",
            noClicarBotao = voltarTelaAnterior,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaDeCepInvalidoComponentPreview() {
    TelaDeCepInvalidoComponent()
}