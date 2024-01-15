package br.com.murilo.busca_cep.ui.component.resultadoCep

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import br.com.murilo.busca_cep.ui.component.comum.TextoComponent
import br.com.murilo.busca_cep.ui.component.comum.TextoCopiavelComponent
import br.com.murilo.busca_cep.ui.extras.tamanhoFontePadrao

@Composable
fun ColunaResultadoComponent(
    modifier: Modifier = Modifier,
    existeElemento: Boolean = true,
    textoDaDescricao: String = "",
    textoDoCampo: String = "",
    fontSize: TextUnit = tamanhoFontePadrao,
    fontWeight: FontWeight = FontWeight.Normal
) {
    if(existeElemento){
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            TextoComponent(
                texto = "$textoDaDescricao: ",
                maxLines = 1,
                fontSize = fontSize,
                fontWeight = fontWeight
            )
            TextoCopiavelComponent(
                texto = textoDoCampo,
                maxLines = 2,
                fontSize = fontSize,
                fontWeight = fontWeight
            )
        }
    }
}