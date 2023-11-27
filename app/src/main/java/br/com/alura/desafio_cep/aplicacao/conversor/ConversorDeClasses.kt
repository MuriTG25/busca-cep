package br.com.alura.desafio_cep.aplicacao.conversor

import br.com.alura.desafio_cep.infraestrutura.modelo.EnderecoResponse
import br.com.alura.desafio_cep.dominio.modelo.Endereco

fun EnderecoResponse.toEndereco(): Endereco{
    return Endereco(
        cep = cep,
        logradouro = logradouro,
        bairro = bairro,
        cidade = cidade,
        estado = estado,
        complemento = complemento
    )
}