package br.com.alura.desafio_cep.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TituloComponent(
    modifier: Modifier = Modifier,
    titulo: String = "",
    tamanhoDaFonte: TextUnit = 24.sp,
    pesoDaFonte: FontWeight = FontWeight.Bold,
    alinhamentoDoTexto: TextAlign = TextAlign.Center
) {
    Text(
        modifier = modifier,
        text = titulo,
        fontSize = tamanhoDaFonte,
        fontWeight = pesoDaFonte,
        textAlign = alinhamentoDoTexto,
        maxLines = 1,
        overflow = TextOverflow.Clip
    )
}

@Preview
@Composable
private fun TituloComponentPreview() {
    TituloComponent(titulo = LoremIpsum(5).values.first())
}