package br.com.murilo.busca_cep.ui.component.comum

import android.os.Build
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import br.com.murilo.busca_cep.ui.extras.margemPadrao
import br.com.murilo.busca_cep.ui.extras.tamanhoFontePadrao

@Composable
fun BotaoComponent(
    modifier: Modifier = Modifier,
    texto: String = "",
    fontSize: TextUnit = tamanhoFontePadrao,
    temImagem: Boolean = false,
    imagem: ImageVector = Icons.Filled.KeyboardArrowDown,
    larguraMinima: Dp = 200.dp,
    corDoBotao:Color = MaterialTheme.colorScheme.onPrimary,
    noClicarBotao: () -> Unit = {}
) {
    Button(
        onClick = noClicarBotao,
        modifier = modifier.widthIn(min= larguraMinima),
        shape = RoundedCornerShape(20),
        ) {
        if (temImagem){
            Icon(
                imageVector = imagem,
                contentDescription = "Icone do Botão"
            )
            Spacer(modifier = Modifier.width(margemPadrao))
        }
        TextoComponent(
            texto = texto,
            fontSize = fontSize,
            color = corDoBotao
        )
    }
}

@Preview
@Composable
private fun BotaoComponentSemImagemPreview() {
    BotaoComponent(texto = "Salvar")
}
@Preview
@Composable
private fun BotaoComponentComImagemPreview() {
    BotaoComponent(
        texto = "Carregar mapa",
        temImagem = true
    )
}