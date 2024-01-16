package br.com.murilo.busca_cep.extra

import br.com.murilo.busca_cep.aplicacao.modelo.EnderecoDTO
import br.com.murilo.busca_cep.aplicacao.modelo.EnderecoResponse

val enderecoParaTestesCompleto = EnderecoDTO(
    cep = "01001-000",
    logradouro = "Praça da Sé",
    complemento = "lado ímpar",
    bairro = "Sé",
    cidade = "São Paulo",
    estado = "SP",
)

val enderecoParaTesteSemComplemento = EnderecoDTO(
    cep = "01001-000",
    logradouro = "Praça da Sé",
    complemento = "",
    bairro = "Sé",
    cidade = "São Paulo",
    estado = "SP",
)
val enderecoResponseParaTestesCompleto = EnderecoResponse(
    cep = "01001-000",
    logradouro = "Praça da Sé",
    complemento = "lado ímpar",
    bairro = "Sé",
    localidade = "São Paulo",
    uf = "SP",
)

val enderecoResponseParaTesteSemComplemento = EnderecoResponse(
    cep = "01001-000",
    logradouro = "Praça da Sé",
    bairro = "Sé",
    localidade = "São Paulo",
    uf = "SP",
)
val enderecoResponseParaTesteVazio = EnderecoResponse()

const val textoVazio = ""
const val textoEmBranco = "   "
const val textoComCep = "13329655"
const val textoCom7Digitos = "1332965"
const val textoCom9Digitos = "133296558"
const val textoComTraco = "13329-655"
const val textoComLetra = "a3329655"
const val textoComVirgula = "13329,655"
const val textoComTracoNoInicio = "-13329655"

