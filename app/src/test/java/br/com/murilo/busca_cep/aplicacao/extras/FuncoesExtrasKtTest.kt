package br.com.murilo.busca_cep.aplicacao.extras

import androidx.compose.ui.text.AnnotatedString
import br.com.murilo.busca_cep.extra.enderecoParaTesteSemComplemento
import br.com.murilo.busca_cep.extra.enderecoParaTestesCompleto
import br.com.murilo.busca_cep.extra.textoCom7Digitos
import br.com.murilo.busca_cep.extra.textoCom9Digitos
import br.com.murilo.busca_cep.extra.textoComCep
import br.com.murilo.busca_cep.extra.textoComLetra
import br.com.murilo.busca_cep.extra.textoComTraco
import br.com.murilo.busca_cep.extra.textoComTracoNoInicio
import br.com.murilo.busca_cep.extra.textoComVirgula
import br.com.murilo.busca_cep.extra.textoEmBranco
import br.com.murilo.busca_cep.extra.textoVazio
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldBeTrue
import org.junit.Test

class FuncoesExtrasKtTest{

    private val textoParaCopiarCompleta = "Praça da Sé, lado ímpar - Sé - São Paulo - SP - 01001-000"
    private val textoParaCopiarSemComplemento = "Praça da Sé, Sé - São Paulo - SP - 01001-000"
    @Test
    fun `dado o EnderecoDTO completo, deve Converter Objeto Em Texto, Quando Utilizarmos a Funcao textoParaCopiar`(){
        val textoParaCopiar = enderecoParaTestesCompleto.textoParaCopiar()
        textoParaCopiar.shouldBeInstanceOf<AnnotatedString>()
        textoParaCopiar.shouldBeEqualTo(AnnotatedString(textoParaCopiarCompleta))
    }
    @Test
    fun `dado o EnderecoDTO sem complemento, deve Converter Objeto Em Texto Sem Complemento, Quando Utilizarmos a Funcao textoParaCopiar`(){
        val textoParaCopiar = enderecoParaTesteSemComplemento.textoParaCopiar()
        textoParaCopiar.shouldBeInstanceOf<AnnotatedString>()
        textoParaCopiar.shouldBeEqualTo(AnnotatedString(textoParaCopiarSemComplemento))
    }
    @Test
    fun `deve retornar False, Quando Verificar String Vazia`(){
        textoVazio.naoEhVazioOuNulo().shouldBeFalse()
    }
    @Test
    fun `deve retornar False, Quando Verificar String em branco`(){
        textoEmBranco.naoEhVazioOuNulo().shouldBeFalse()
    }
    @Test
    fun `deve retornar True, Quando Verificar String Com Texto`(){
        textoComCep.naoEhVazioOuNulo().shouldBeTrue()
    }
    @Test
    fun `deve retornar False, Quando verificar String com letra`(){
        textoComLetra.ehNumeroSemSimbolos().shouldBeFalse()
    }
    @Test
    fun `deve retornar False, Quando verificar String com simbolo`(){
        textoComTraco.ehNumeroSemSimbolos().shouldBeFalse()
    }
    @Test
    fun `deve retornar False, Quando verificar String com valor negativo`(){
        textoComTracoNoInicio.ehNumeroSemSimbolos().shouldBeFalse()
    }
    @Test
    fun `deve retornar False, Quando verificar String com numero com virgula`(){
        textoComVirgula.ehNumeroSemSimbolos().shouldBeFalse()
    }
    @Test
    fun `deve retornar True, Quando verificar String com numero inteiro`(){
        textoComCep.ehNumeroSemSimbolos().shouldBeTrue()
    }
    @Test
    fun `deve retornar True, Quando verificar String com 7 digitos`(){
        textoCom7Digitos.naoTem8Digitos().shouldBeTrue()
    }
    @Test
    fun `deve retornar True, Quando verificar String com 9 digitos`(){
        textoCom9Digitos.naoTem8Digitos().shouldBeTrue()
    }
    @Test
    fun `deve retornar False, Quando verificar String com 8 digitos`(){
        textoComCep.naoTem8Digitos().shouldBeFalse()
    }

}