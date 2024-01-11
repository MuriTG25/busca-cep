package br.com.murilo.busca_cep.aplicacao.repositorio

import br.com.murilo.busca_cep.aplicacao.modelo.EnderecoResponse
import br.com.murilo.busca_cep.infraestrutura.webservice.CepBuscaService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BuscaCepRepositorio @Inject constructor(
    private val service:CepBuscaService
) {
    suspend fun buscaCep(cep:String):Flow<EnderecoResponse?>{
        return flow {
            emit(service.buscaCep(cep))
        }.catch {e->
            if (e is Exception){
                emptyFlow<EnderecoResponse>()
            }
        }
    }
}