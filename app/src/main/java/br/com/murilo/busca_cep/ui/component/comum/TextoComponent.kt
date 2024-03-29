package br.com.murilo.busca_cep.ui.component.comum

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.TextUnit
import br.com.murilo.busca_cep.ui.extras.tamanhoFontePadrao


@Composable
fun TextoComponent(
    modifier: Modifier = Modifier,
    texto: String,
    maxLines: Int = 2,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = tamanhoFontePadrao,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    Text(
        modifier = modifier,
        text = texto,
        maxLines = maxLines,
        textAlign = textAlign,
        fontSize = fontSize,
        overflow = overflow,
        fontWeight = fontWeight,
        color = color,
    )
}
@Composable
fun TextoCopiavelComponent(
    modifier: Modifier = Modifier,
    texto: String,
    maxLines: Int = 2,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = tamanhoFontePadrao,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    SelectionContainer {
        TextoComponent(
            modifier = modifier,
            texto = texto,
            maxLines = maxLines,
            textAlign = textAlign,
            fontSize = fontSize,
            overflow = overflow,
            fontWeight = fontWeight,
            color = color,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun TextoComponentPreview() {
    TextoComponent(texto = LoremIpsum(10).values.toString())
}