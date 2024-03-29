package br.com.murilo.busca_cep.ui.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.murilo.busca_cep.aplicacao.extras.CEP_VALOR
import br.com.murilo.busca_cep.aplicacao.extras.textoParaCopiar
import br.com.murilo.busca_cep.aplicacao.modelo.EnderecoResponse
import br.com.murilo.busca_cep.aplicacao.modelo.toEnderecoDTO
import br.com.murilo.busca_cep.aplicacao.repositorio.BuscaCepRepositorio
import br.com.murilo.busca_cep.ui.stateholder.ResultadoCepUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultadoCepViewModel @Inject constructor(
    private val repositorio: BuscaCepRepositorio,
    stateHandle: SavedStateHandle,
):ViewModel(){
    private val _uiState = MutableStateFlow<ResultadoCepUiState>(
        ResultadoCepUiState.Carregando
    )
    val uiState = _uiState.asStateFlow()
    private val cep = stateHandle.get<String>(CEP_VALOR)
    private var job: Job? = null
    init {
        cep?.let {
            carregaEndereco(it)
        }
    }

    fun carregaEndereco(cep:String) {
       job?.cancel()
       job = viewModelScope.launch {
           repositorio.buscaCep(cep).onStart {
               telaDeCarregamento()
           }.lastOrNull()?.let {endereco->
               if(endereco.erro){
                   telaDeCepInvalido()
               } else{
                   telaDeSucesso(endereco)
               }
           } ?: telaDeFalha()
       }

    }

    private fun telaDeCarregamento(){
        _uiState.update {
            ResultadoCepUiState.Carregando
        }
    }

    private fun telaDeSucesso(endereco: EnderecoResponse) {
        _uiState.update {
            with(endereco.toEnderecoDTO()) {
                ResultadoCepUiState.Sucesso(
                    cep = cep,
                    logradouro = logradouro,
                    bairro = bairro,
                    cidade = cidade,
                    estado = estado,
                    complemento = complemento,
                    textoParaCopiar = textoParaCopiar()
                )
            }
        }
    }
    private fun telaDeFalha(){
        _uiState.update {
            ResultadoCepUiState.Falha
        }
    }
    private fun telaDeCepInvalido(){
        _uiState.update {
            ResultadoCepUiState.CepInvalido
        }
    }
}