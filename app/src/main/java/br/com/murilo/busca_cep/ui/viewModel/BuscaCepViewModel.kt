package br.com.murilo.busca_cep.ui.viewModel

import androidx.lifecycle.ViewModel
import br.com.murilo.busca_cep.aplicacao.extras.ehNumeroSemSimbolos
import br.com.murilo.busca_cep.aplicacao.extras.naoEhVazioOuNulo
import br.com.murilo.busca_cep.aplicacao.extras.naoTem8Digitos
import br.com.murilo.busca_cep.ui.stateholder.BuscaCepUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BuscaCepViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(BuscaCepUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { buscaCepUiState ->
            buscaCepUiState.copy(
                alteracaoDoCep = {
                    if (it.length < 9) {
                        _uiState.value = _uiState.value.copy(
                            cep = it,
                            mensagemCampoVazio = false
                        )
                    }
                },
                naAlteracaoDaMensagemCepMenos8Digitos = {
                    _uiState.value = _uiState.value.copy(
                        mensagemCepMenos8Digitos = it
                    )
                }
            )

        }
    }

    fun buscaCep(
        navegarParaTelaDeResultado: (String) -> Unit = {},
    ) {
        val cep = _uiState.value.cep
        if (!cep.naoEhVazioOuNulo()) {
            _uiState.update {
                it.copy(
                    mensagemCampoVazio = true
                )
            }
        } else if (cep.naoTem8Digitos() || !cep.ehNumeroSemSimbolos()) {
            _uiState.update {
                it.copy(
                    mensagemCepMenos8Digitos = true
                )
            }
        }
        else {
            navegarParaTelaDeResultado(cep)
        }
    }
}