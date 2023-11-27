package br.com.alura.desafio_cep.ui.screen.stateless

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.desafio_cep.ui.component.BotaoDeSalvarComponent
import br.com.alura.desafio_cep.ui.component.CampoDeTextoComponent
import br.com.alura.desafio_cep.ui.component.TituloComponent
import br.com.alura.desafio_cep.ui.component.TransformadorDeCep
import br.com.alura.desafio_cep.ui.margemPadrao
import br.com.alura.desafio_cep.ui.theme.DesafiocepTheme
import br.com.alura.desafio_cep.ui.uiState.CepBuscaUiState

@Composable
fun CepBuscaScreen(
    modifier: Modifier = Modifier,
    state: CepBuscaUiState = CepBuscaUiState(),
    noClicarSalvar: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(margemPadrao),
        verticalArrangement = Arrangement.spacedBy(margemPadrao/2)
        ) {
        val modifierPadrao = Modifier.fillMaxWidth()
        TituloComponent(
            modifier = modifierPadrao,
            titulo = "Cadastro De Endereço",
        )
        CampoDeTextoComponent(
            modifier = modifierPadrao,
            nomeDaLabel = "CEP",
            valor = state.cep,
            naMudancaDeValor = state.naMudancaDeCep,
            tipoDeTeclado = KeyboardType.Number,
            transformacaoVisual = TransformadorDeCep()
        )
        CampoDeTextoComponent(
            modifier = modifierPadrao,
            nomeDaLabel = "Logradouro",
            valor = state.logradouro,
            naMudancaDeValor = state.naMudancaDeLogradouro,
            maiuscula = KeyboardCapitalization.Sentences,
        )
        CampoDeTextoComponent(
            modifier = modifierPadrao,
            nomeDaLabel = "Número",
            valor = state.numero,
            naMudancaDeValor = state.naMudancaDeNumero,
            tipoDeTeclado = KeyboardType.Number,
        )
        CampoDeTextoComponent(
            modifier = modifierPadrao,
            nomeDaLabel = "Bairro",
            valor = state.bairro,
            naMudancaDeValor = state.naMudancaDeBairro,
        )
        CampoDeTextoComponent(
            modifier = modifierPadrao,
            nomeDaLabel = "Cidade",
            valor = state.cidade,
            naMudancaDeValor = state.naMudancaDeCidade,
        )
        CampoDeTextoComponent(
            modifier = modifierPadrao,
            nomeDaLabel = "Estado",
            valor = state.estado,
            naMudancaDeValor = state.naMudancaDeEstado,
        )
        CampoDeTextoComponent(
            modifier = modifierPadrao,
            nomeDaLabel = "Complemento",
            valor = state.complemento,
            naMudancaDeValor = state.naMudancaDeComplemento,
            maiuscula = KeyboardCapitalization.Sentences,
            acaoDoBotaoEnter = ImeAction.Done
        )
        BotaoDeSalvarComponent(
            modifier = modifierPadrao,
            noClicarSalvar = noClicarSalvar
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CepBuscaScreenPreview() {
    DesafiocepTheme {
        Surface {
            CepBuscaScreen()
        }
    }
}