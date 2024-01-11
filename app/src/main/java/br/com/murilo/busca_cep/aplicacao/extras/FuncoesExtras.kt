package br.com.murilo.busca_cep.aplicacao.extras

import androidx.compose.ui.text.AnnotatedString
import br.com.murilo.busca_cep.aplicacao.modelo.EnderecoDTO

fun EnderecoDTO.textoParaCopiar(): AnnotatedString {
    val textoComplemento = if (complemento.isEmpty()) ""
     else " $complemento -"
    return AnnotatedString("$logradouro,$textoComplemento $bairro - $cidade - $estado - $cep")
}