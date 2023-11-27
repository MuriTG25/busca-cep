package br.com.alura.desafio_cep.infraestrutura.network.services

import br.com.alura.desafio_cep.infraestrutura.modelo.EnderecoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CepBuscaService {
    @GET("{cep}/json/")
    suspend fun encontraEnderecoPeloCep(@Path("cep") cep:String): EnderecoResponse
}