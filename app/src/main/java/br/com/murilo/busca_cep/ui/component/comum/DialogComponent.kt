package br.com.murilo.busca_cep.ui.component.comum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Dialog
import br.com.murilo.busca_cep.ui.extras.alturaDialogPadrao
import br.com.murilo.busca_cep.ui.extras.larguraDialogPadrao
import br.com.murilo.busca_cep.ui.extras.margemPadrao
import br.com.murilo.busca_cep.ui.extras.tamanhoCaixaPadrao

@Composable
fun DialogComponent(
    modifier: Modifier = Modifier,
    noClickSair: () -> Unit = {},
    alturaMinima: Dp = tamanhoCaixaPadrao,
    alturaMaxima: Dp = alturaDialogPadrao,
    larguraMinima: Dp = larguraDialogPadrao,
    larguraMaxima: Dp = larguraDialogPadrao,
    corDeFundo: Color = MaterialTheme.colorScheme.onTertiary,
    conteudo: @Composable () -> Unit ={},
) {
    Dialog(onDismissRequest = noClickSair) {
        Column(
            modifier
                .clip(RoundedCornerShape(5))
                .heightIn(alturaMinima, alturaMaxima)
                .widthIn(larguraMinima,larguraMaxima)
                .background(corDeFundo)
                .padding(margemPadrao),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(margemPadrao)
        ) {
            conteudo()
        }
    }
}