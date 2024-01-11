package br.com.murilo.busca_cep.infraestrutura.webservice

import br.com.murilo.busca_cep.aplicacao.modelo.EnderecoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

private const val urlBase = "https://viacep.com.br/ws/"

private const val tipoResponse = "/json/"

private fun enderecoCompleto(cep: String) = urlBase+cep+ tipoResponse

class CepBuscaService @Inject constructor(
    private val client: HttpClient
){
    suspend fun buscaCep(cep: String): EnderecoResponse {
        return client.get(enderecoCompleto(cep))
            .body<EnderecoResponse>()
    }
}