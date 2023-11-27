package br.com.alura.desafio_cep.aplicacao.repository

import android.util.Log
import br.com.alura.desafio_cep.aplicacao.conversor.toEndereco
import br.com.alura.desafio_cep.dominio.modelo.Endereco
import br.com.alura.desafio_cep.infraestrutura.network.services.CepBuscaService
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class CepBuscaRepository @Inject constructor(
    val service: CepBuscaService,
) {
    suspend fun encontraEnderecoPeloCep(cep: String): Endereco? {
        return try {
            val response = service.encontraEnderecoPeloCep(cep)
            response.toEndereco()
        } catch (e: ConnectException) {
            Log.e("MovieRepository", "encontraEnderecoPeloCep: falha ao conectar na API")
            null
        } catch (e: SocketTimeoutException) {
            Log.e("MovieRepository", "encontraEnderecoPeloCep: Demora na conexão da API")
            null
        }catch (e: HttpException) {
            Log.e("MovieRepository", "encontraEnderecoPeloCep: Cep inválido")
            null
        }
    }
}
