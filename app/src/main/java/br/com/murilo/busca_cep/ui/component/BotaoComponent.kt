package br.com.murilo.busca_cep.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import br.com.murilo.busca_cep.ui.extras.margemPadrao
import br.com.murilo.busca_cep.ui.extras.tamanhoFontePadrao

@Composable
fun BotaoComponent(
    modifier: Modifier = Modifier,
    texto: String = "",
    fontSize: TextUnit = tamanhoFontePadrao,
    temImagem: Boolean = false,
    imagem: ImageVector = Icons.Filled.KeyboardArrowDown,
    noClicarBotao: () -> Unit = {}
) {
    Button(
        onClick = noClicarBotao,
        modifier = modifier,
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),


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
            color = MaterialTheme.colorScheme.onPrimary
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