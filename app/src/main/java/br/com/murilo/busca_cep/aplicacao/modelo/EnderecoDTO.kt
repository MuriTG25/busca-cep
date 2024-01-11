package br.com.murilo.busca_cep.aplicacao.modelo

data class EnderecoDTO(
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val complemento: String,
)

fun EnderecoResponse.toEnderecoDTO():EnderecoDTO{
    return EnderecoDTO(
        cep = cep ?: "",
        logradouro = logradouro ?: "",
        bairro = bairro ?: "",
        cidade = localidade ?: "",
        estado = uf  ?: "",
        complemento = complemento ?: "",
    )
}