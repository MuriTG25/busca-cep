package br.com.alura.desafio_cep.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoDeTextoComponent(
    modifier: Modifier = Modifier,
    nomeDaLabel: String = "",
    dicaDoCampo: String = "",
    valor: String = "",
    naMudancaDeValor: (String) -> Unit = {},
    tipoDeTeclado: KeyboardType = KeyboardType.Text,
    acaoDoBotaoEnter: ImeAction = ImeAction.Next,
    maiuscula: KeyboardCapitalization = KeyboardCapitalization.Words,
    maximoDeLinhas: Int = 1,
    transformacaoVisual:VisualTransformation = VisualTransformation.None

    ) {
    OutlinedTextField(
        modifier = modifier,
        value = valor,
        onValueChange = naMudancaDeValor,
        label = {
            Text(text = nomeDaLabel)
        },
        shape = RoundedCornerShape(20),
        keyboardOptions = KeyboardOptions(
            keyboardType = tipoDeTeclado,
            imeAction = acaoDoBotaoEnter,
            capitalization = maiuscula
        ),
        maxLines = maximoDeLinhas,
        placeholder = {
            Text(text = dicaDoCampo)
        },
        visualTransformation = transformacaoVisual
    )
}

@Preview(showBackground = true)
@Composable
fun CampoDeTextoComponentPreviewComTexto() {
    CampoDeTextoComponent(nomeDaLabel = "Nome", valor = "teste")
}
@Preview(showBackground = true)
@Composable
fun CampoDeTextoComponentPreviewSemTexto() {
    CampoDeTextoComponent(nomeDaLabel = "Nome")
}