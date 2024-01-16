package br.com.murilo.busca_cep.aplicacao.repositorio

import br.com.murilo.busca_cep.aplicacao.modelo.EnderecoResponse
import br.com.murilo.busca_cep.infraestrutura.webservice.CepBuscaService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import org.junit.Rule

class BuscaCepRepositorioTest{
    @MockK
    private lateinit var service: CepBuscaService
    @InjectMockKs
    lateinit var repositorio: BuscaCepRepositorio
    @get:Rule
    val mockkRule = MockKRule(this)

    private val enderecoMockado = mockk<EnderecoResponse> {
        every { cep } returns "01001000"
        every { logradouro } returns "Praça da Sé"
        every { complemento } returns "lado ímpar"
        every { bairro } returns "Sé"
        every { localidade } returns "São Paulo"
        every { uf } returns "SP"
    }
    //TODO preciso corrigir esse teste
//    @Test
//    fun `deve chamar o buscaCep do Service, quando chamar buscaCep do Repositorio`():Unit
//    = runBlocking{
//        coEvery {
//            service.buscaCep("01001000")
//        } returns enderecoMockado
//        repositorio.buscaCep("01001000")
//        coVerify {
//            flow {
//                emit(service.buscaCep("01001000"))
//            }
//        }
//    }

}