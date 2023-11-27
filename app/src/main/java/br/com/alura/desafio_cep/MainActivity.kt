package br.com.alura.desafio_cep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.desafio_cep.ui.screen.statefull.CepBuscaScreen
import br.com.alura.desafio_cep.ui.theme.DesafiocepTheme
import br.com.alura.desafio_cep.ui.viewModel.CepBuscaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesafiocepTheme(){
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val viewModel by viewModels<CepBuscaViewModel>()
                    CepBuscaScreen(viewModel = viewModel)
                }
            }
        }
    }
}