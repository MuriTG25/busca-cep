package br.com.murilo.busca_cep.ui.viewModel

import androidx.lifecycle.ViewModel
import br.com.murilo.busca_cep.aplicacao.repositorio.BuscaCepRepositorio
import br.com.murilo.busca_cep.ui.stateholder.BuscaCepUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BuscaCepViewModel @Inject constructor(
): ViewModel(){
    private val _uiState = MutableStateFlow(BuscaCepUiState())
    val uiState = _uiState.asStateFlow()
    init {
        _uiState.update {buscaCepUiState->
            buscaCepUiState.copy(
                alteracaoDoCep = {
                    _uiState.value = _uiState.value.copy(
                        cep = it
                    )
                }
            )

        }
    }
}