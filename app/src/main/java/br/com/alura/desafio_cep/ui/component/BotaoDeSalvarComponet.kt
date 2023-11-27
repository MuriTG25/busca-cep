package br.com.alura.desafio_cep.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BotaoDeSalvarComponent(
    modifier:Modifier = Modifier,
    noClicarSalvar: ()-> Unit = {}
) {
    Button(
        modifier = modifier,
        onClick = noClicarSalvar,
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Text(text = "Salvar")
    }
}

@Preview
@Composable
private fun BotaoDeSalvarComponentPreview() {
    BotaoDeSalvarComponent()
}