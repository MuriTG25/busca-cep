package br.com.alura.desafio_cep.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.desafio_cep.aplicacao.repository.CepBuscaRepository
import br.com.alura.desafio_cep.dominio.modelo.Endereco
import br.com.alura.desafio_cep.ui.uiState.CepBuscaUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CepBuscaViewModel @Inject constructor(
    private val repository: CepBuscaRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CepBuscaUiState>(CepBuscaUiState())
    val uiState get() = _uiState.asStateFlow()
    private var job: Job = Job()

    init {
        _uiState.update { cepBuscaUiState ->
            cepBuscaUiState.copy(
                naMudancaDeCep = {
                    if(it.length < 9){
                        _uiState.value = _uiState.value.copy(
                            cep = it
                        )
                        buscarEndereco(it)
                    }
                },
                naMudancaDeLogradouro = {
                    _uiState.value = _uiState.value.copy(
                        logradouro = it
                    )
                },
                naMudancaDeNumero = {
                    _uiState.value = _uiState.value.copy(
                        numero = it
                    )
                },
                naMudancaDeBairro = {
                    _uiState.value = _uiState.value.copy(
                        bairro = it
                    )
                },
                naMudancaDeCidade = {
                    _uiState.value = _uiState.value.copy(
                        cidade = it
                    )
                },
                naMudancaDeEstado = {
                    _uiState.value = _uiState.value.copy(
                        estado = it
                    )
                },
                naMudancaDeComplemento = {
                    _uiState.value = _uiState.value.copy(
                        complemento = it
                    )
                },
            )
        }
    }

    private fun buscarEndereco(cep:String) {
        job.cancel()
        job = viewModelScope.launch {
            delay(2000)
            Log.i("CepBuscaViewModel","CEP: $cep")
            repository.encontraEnderecoPeloCep(cep)?.let { endereco ->
                _uiState.update {
                    it.copy(
                        logradouro = endereco.logradouro,
                        bairro = endereco.bairro,
                        cidade = endereco.cidade,
                        estado = endereco.estado,
                        complemento = endereco.complemento
                    )
                }
            }
        }
    }

    fun salvar() {
        with(_uiState.value) {
            val endereco = Endereco(
                cep = cep,
                logradouro = logradouro,
                numero = numero,
                bairro = bairro,
                cidade = cidade,
                estado = estado,
                complemento = complemento
            )
            Log.i("CepBuscaViewModel","Endereco salvo = $endereco")
        }
    }
}