package br.com.alura.desafio_cep.aplicacao.repository

import android.util.Log
import br.com.alura.desafio_cep.aplicacao.conversor.toEndereco
import br.com.alura.desafio_cep.dominio.modelo.Endereco
import br.com.alura.desafio_cep.infraestrutura.network.services.CepBuscaService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class CepBuscaRepository @Inject constructor(
    val service: CepBuscaService,
) {
    suspend fun encontraEnderecoPeloCep(cep: String): Flow<Endereco?> {
        return flow{
            emit(
                service.encontraEnderecoPeloCep(cep).toEndereco()
            )
        }.catch {e->
            when (e) {
                is ConnectException, is SocketTimeoutException, is HttpException ->{
                    emptyFlow<Endereco>()
                    Log.i("CepBuscaRepository","Excecao pega: ${e.message}")
                }
            }
        }

    }
}
