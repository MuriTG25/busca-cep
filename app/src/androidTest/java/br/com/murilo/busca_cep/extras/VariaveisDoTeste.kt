package br.com.murilo.busca_cep.extras

import br.com.murilo.busca_cep.aplicacao.modelo.EnderecoResponse

const val descricaoImagemBuscaCep = "Logo do Aplicativo"
const val textoCampoTextoCep = "CEP"
const val dicaCampoTextoCep = "Digite o CEP (8 dígitos, sem o -)"
const val textoBotaoBuscarEndereco = "Buscar Endereço"
const val mensagemDeErroCampoObrigatorio = "*O campo do CEP é obrigatório"

const val mensagemDialogCep = "Digite 8 caracteres para o CEP, e não insira simbolos como o traço \"-\""
const val textoBotaoVoltarDialog = "Voltar"

val enderecoResponseParaTestesCompleto = EnderecoResponse(
    cep = "01001-000",
    logradouro = "Praça da Sé",
    complemento = "lado ímpar",
    bairro = "Sé",
    localidade = "São Paulo",
    uf = "SP",
)

const val textoBotaoCopiarTudo = "Copiar tudo"

const val textoEmBranco = "   "
const val textoComCep = "13329655"
const val textoComCepNaTela = "13329-655"
const val textoCom7Digitos = "1332965"
const val textoCom9Digitos = "133296558"
const val textoComTraco = "13329-55"
const val textoComLetra = "a332955"
const val textoComVirgula = "13329,55"
const val textoComTracoNoInicio = "-1332965"
