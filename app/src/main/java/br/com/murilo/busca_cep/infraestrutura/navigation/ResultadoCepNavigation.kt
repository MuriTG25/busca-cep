package br.com.murilo.busca_cep.infraestrutura.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.murilo.busca_cep.aplicacao.extras.CEP_VALOR
import br.com.murilo.busca_cep.ui.screen.ResultadoCepScreen
import br.com.murilo.busca_cep.ui.viewModel.ResultadoCepViewModel

private const val rotaResultadoCep = "resultadoCep"
internal const val rotaResultadoCepCompleta = "$rotaResultadoCep/{$CEP_VALOR}"

fun NavGraphBuilder.ResultadoCepNavController(
    navegarParaTelaAnterior: () -> Unit = {},
){
    composable(route = rotaResultadoCepCompleta){backStackEntry->
        backStackEntry.arguments?.getString(CEP_VALOR)?.let {cep->
            val viewModel = hiltViewModel<ResultadoCepViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            ResultadoCepScreen(
                uiState = uiState,
                aoTentarBuscarNovamenteOEndereco = {
                    viewModel.carregaEndereco(cep)
                },
                navegarAParaTelaAnterior = navegarParaTelaAnterior,
            )
        }
    }
}

fun NavController.navegarParaResultadoCep(cep: String) {
    navigate("$rotaResultadoCep/$cep") {
        launchSingleTop = true
        popBackStack("$rotaResultadoCep/$cep", true)
    }
}