package br.com.murilo.busca_cep.ui.component.buscaCep

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.murilo.busca_cep.ui.component.comum.BotaoComponent
import br.com.murilo.busca_cep.ui.component.comum.DialogComponent
import br.com.murilo.busca_cep.ui.component.comum.TextoComponent
import br.com.murilo.busca_cep.ui.extras.alturaDialogPequenaMin
import br.com.murilo.busca_cep.ui.extras.tamanhoCaixaPadrao

@Composable
fun DialogErroDigitoComponent(
    modifier: Modifier = Modifier,
    fecharDialog: () -> Unit = {},
) {
    DialogComponent(
        modifier = modifier,
        alturaMinima = alturaDialogPequenaMin,
        noClickSair = fecharDialog
    ) {
        TextoComponent(
            texto = "Digite 8 caracteres para o CEP, e não insira simbolos como o traço \"-\"",
            maxLines = 4,
            textAlign = TextAlign.Center
        )
        BotaoComponent(texto = "Voltar", noClicarBotao = fecharDialog)
    }

}
@Preview(showBackground = true)
@Composable
private fun DialogErroDigitoComponentPreview() {
    DialogErroDigitoComponent()
}