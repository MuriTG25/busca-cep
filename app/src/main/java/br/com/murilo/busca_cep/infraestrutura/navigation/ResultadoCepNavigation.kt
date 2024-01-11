package br.com.murilo.busca_cep.infraestrutura.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.murilo.busca_cep.ui.screen.ResultadoCepScreen
import br.com.murilo.busca_cep.ui.viewModel.ResultadoCepViewModel

internal const val rotaResultadoCep = "resultadoCep"

fun NavGraphBuilder.ResultadoCepNavController(
    navegarParaTelaAnterior: () -> Unit = {},
){
    composable(route = rotaResultadoCep){
        val viewModel = hiltViewModel<ResultadoCepViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        ResultadoCepScreen(
            uiState = uiState,
            aoTentarBuscarNovamenteOEndereco = {
                viewModel.carregaEndereco()
            },
            navegarParaTelaAnterior = navegarParaTelaAnterior

        )
    }
}

fun NavController.navegarParaResultadoCep() {
    navigate(rotaResultadoCep) {
        launchSingleTop = true
        popUpTo(rotaResultadoCep) {
            inclusive = false
        }
    }
}