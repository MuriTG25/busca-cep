package br.com.murilo.busca_cep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import br.com.murilo.busca_cep.infraestrutura.navigation.BuscaCepNavHost
import br.com.murilo.busca_cep.ui.screen.ResultadoCepScreen
import br.com.murilo.busca_cep.ui.theme.BuscacepTheme
import br.com.murilo.busca_cep.ui.viewModel.ResultadoCepViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuscacepTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    BuscaCepNavHost(navHostController = navController)
                }
            }
        }
    }
}