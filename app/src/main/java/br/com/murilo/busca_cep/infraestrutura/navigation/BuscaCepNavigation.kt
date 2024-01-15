package br.com.murilo.busca_cep.infraestrutura.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.murilo.busca_cep.ui.screen.BuscaCepScreen
import br.com.murilo.busca_cep.ui.viewModel.BuscaCepViewModel

internal const val buscaCepRota = "buscaCep"
fun NavGraphBuilder.BuscaCepNavController(
    navegarParaTelaResultado: (String) -> Unit = {},
){
    composable(buscaCepRota){
        val viewModel = hiltViewModel<BuscaCepViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        BuscaCepScreen(
            uiState = uiState,
            navegarParaTelaResultado = {
                viewModel.buscaCep{cep->
                    navegarParaTelaResultado(cep)
                }
            }
        )
    }
}
fun NavController.navegarParaBuscaCep() {
    navigate(buscaCepRota) {
        launchSingleTop = true
        popUpTo(graph.id) {
            inclusive = true
        }
    }
}