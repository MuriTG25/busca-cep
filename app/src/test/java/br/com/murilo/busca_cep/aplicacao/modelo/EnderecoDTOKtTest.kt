package br.com.murilo.busca_cep.aplicacao.modelo

import br.com.murilo.busca_cep.extra.enderecoResponseParaTesteSemComplemento
import br.com.murilo.busca_cep.extra.enderecoResponseParaTesteVazio
import br.com.murilo.busca_cep.extra.enderecoResponseParaTestesCompleto
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldBeTrue
import org.junit.Assert.*
import org.junit.Test

class EnderecoDTOKtTest{
    private val enderecoToStringCompleta =
        "EnderecoDTO(cep=01001-000, logradouro=Praça da Sé, bairro=Sé, cidade=São Paulo, estado=SP, complemento=lado ímpar)"
    private val enderecoToStringSemComplemento =
        "EnderecoDTO(cep=01001-000, logradouro=Praça da Sé, bairro=Sé, cidade=São Paulo, estado=SP, complemento=)"
    private val enderecoToStringVazia =
        "EnderecoDTO(cep=, logradouro=, bairro=, cidade=, estado=, complemento=)"
    @Test
    fun `dado um EnderecoResponse completo, deve retornar um EnderecoDTO, quando converter com toEnderecoDTO`(){
        val enderecoDTO = enderecoResponseParaTestesCompleto.toEnderecoDTO()
        enderecoDTO.shouldBeInstanceOf<EnderecoDTO>()
        enderecoDTO.toString().shouldBeEqualTo(enderecoToStringCompleta)
    }
    @Test
    fun `dado um EnderecoResponse sem Complemento, deve retornar um EnderecoDTO com complemento em String vazia, quando converter com toEnderecoDTO`(){
        val enderecoDTO = enderecoResponseParaTesteSemComplemento.toEnderecoDTO()
        enderecoResponseParaTesteSemComplemento.complemento.shouldBeNull()
        enderecoDTO.shouldBeInstanceOf<EnderecoDTO>()
        enderecoDTO.complemento.isBlank().shouldBeTrue()
        enderecoDTO.toString().shouldBeEqualTo(enderecoToStringSemComplemento)
    }
    @Test
    fun `dado um EnderecoResponse vazio, deve retornar um EnderecoDTO com String vazia em tudo, quando converter com toEnderecoDTO`(){
        val enderecoDTO = enderecoResponseParaTesteVazio.toEnderecoDTO()
        enderecoDTO.shouldBeInstanceOf<EnderecoDTO>()
        enderecoDTO.toString().shouldBeEqualTo(enderecoToStringVazia)
    }
}