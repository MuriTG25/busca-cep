package br.com.murilo.busca_cep.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TelaDeCarregamentoComponent() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            Modifier.align(Alignment.Center)
                .semantics {
                    contentDescription = "Tela de Carregamento"
                }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaDeCarregamentoComponetPreview() {
    TelaDeCarregamentoComponent()
}