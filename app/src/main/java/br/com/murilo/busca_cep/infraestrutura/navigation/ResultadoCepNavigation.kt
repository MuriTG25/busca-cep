package br.com.murilo.busca_cep.infraestrutura.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.murilo.busca_cep.aplicacao.extras.cepValor
import br.com.murilo.busca_cep.ui.screen.ResultadoCepScreen
import br.com.murilo.busca_cep.ui.viewModel.ResultadoCepViewModel

private const val rotaResultadoCep = "resultadoCep"
internal const val rotaResultadoCepCompleta = "$rotaResultadoCep/{$cepValor}"

fun NavGraphBuilder.ResultadoCepNavController(
    navegarParaTelaAnterior: () -> Unit = {},
){
    composable(route = rotaResultadoCepCompleta){
        val viewModel = hiltViewModel<ResultadoCepViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        ResultadoCepScreen(
            uiState = uiState,
            aoTentarBuscarNovamenteOEndereco = {
                viewModel.carregaEndereco()
            },
            navegarParaTelaAnterior = navegarParaTelaAnterior,
        )
    }
}

fun NavController.navegarParaResultadoCep(cep: String) {
    navigate("$rotaResultadoCep/{${cep}}") {
        launchSingleTop = true
        popUpTo(rotaResultadoCep) {
            inclusive = false
        }
    }
}