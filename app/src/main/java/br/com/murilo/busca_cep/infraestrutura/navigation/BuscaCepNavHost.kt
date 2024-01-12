package br.com.murilo.busca_cep.infraestrutura.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun BuscaCepNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = rotaResultadoCepCompleta
    ) {
        ResultadoCepNavController(
            navegarParaTelaAnterior = {
                navHostController.popBackStack()
            },
        )
        BuscaCepNavController(
            navegarParaTelaResultado = { cep ->
                navHostController.navegarParaResultadoCep(cep)
            }
        )
    }
}