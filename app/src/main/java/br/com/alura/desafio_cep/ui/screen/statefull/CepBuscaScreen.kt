package br.com.alura.desafio_cep.ui.screen.statefull

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.com.alura.desafio_cep.ui.screen.stateless.CepBuscaScreen
import br.com.alura.desafio_cep.ui.viewModel.CepBuscaViewModel

@Composable
fun CepBuscaScreen(viewModel: CepBuscaViewModel){
    val state by viewModel.uiState.collectAsState()
    CepBuscaScreen(
        state = state,
        noClicarSalvar = { viewModel.salvar() }
    )
}