package br.com.murilo.busca_cep.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TelaDeFalhaComponent(
    aoTentarBuscarNovamenteOEndereco: () -> Unit = {},
    voltarTelaAnterior: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Falha ao buscar o Endereço")
        Button(onClick = aoTentarBuscarNovamenteOEndereco) {
            Text(text = "Recarregar página")
        }
        Button(onClick = voltarTelaAnterior) {
            Text(text = "Voltar")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaDeFalhaComponentPreview() {
    TelaDeCarregamentoComponent()
}