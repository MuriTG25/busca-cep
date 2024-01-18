package br.com.murilo.busca_cep.ui.screen

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import br.com.murilo.busca_cep.MainActivity
import br.com.murilo.busca_cep.extras.clicaNoCanto
import br.com.murilo.busca_cep.extras.clicaNoElementoPeloNome
import br.com.murilo.busca_cep.extras.descricaoImagemBuscaCep
import br.com.murilo.busca_cep.extras.dicaCampoTextoCep
import br.com.murilo.busca_cep.extras.digitaNoCampoDeTexto
import br.com.murilo.busca_cep.extras.esperaAteATelaAparecer
import br.com.murilo.busca_cep.extras.fakeClient
import br.com.murilo.busca_cep.extras.mensagemDeErroCampoObrigatorio
import br.com.murilo.busca_cep.extras.mensagemDialogCep
import br.com.murilo.busca_cep.extras.minimizarOAppEReabrir
import br.com.murilo.busca_cep.extras.mockEngineCerto
import br.com.murilo.busca_cep.extras.rotacionarATela
import br.com.murilo.busca_cep.extras.scrollaAteOElementoPeloNome
import br.com.murilo.busca_cep.extras.textoBotaoBuscarEndereco
import br.com.murilo.busca_cep.extras.textoBotaoCopiarTelaSucesso
import br.com.murilo.busca_cep.extras.textoBotaoVoltarDialog
import br.com.murilo.busca_cep.extras.textoCampoTextoCep
import br.com.murilo.busca_cep.extras.textoCom1Caractere
import br.com.murilo.busca_cep.extras.textoCom7Digitos
import br.com.murilo.busca_cep.extras.textoCom9Digitos
import br.com.murilo.busca_cep.extras.textoCom9DigitosNaTela
import br.com.murilo.busca_cep.extras.textoComCep
import br.com.murilo.busca_cep.extras.textoComCepNaTela
import br.com.murilo.busca_cep.extras.textoComLetra
import br.com.murilo.busca_cep.extras.textoComTraco
import br.com.murilo.busca_cep.extras.textoComTracoNoInicio
import br.com.murilo.busca_cep.extras.textoComVirgula
import br.com.murilo.busca_cep.extras.textoEmBranco
import br.com.murilo.busca_cep.extras.verificaSeExisteOComponentPeloTexto
import br.com.murilo.busca_cep.extras.verificaSeMostraOComponentePelaDescricao
import br.com.murilo.busca_cep.extras.verificaSeMostraOComponentePeloTexto
import br.com.murilo.busca_cep.extras.verificaSeNaoExisteOComponentePeloTexto
import br.com.murilo.busca_cep.extras.voltarARotacaoDaTela
import br.com.murilo.busca_cep.infraestrutura.di.module.RestApiModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@UninstallModules(RestApiModule::class)
@HiltAndroidTest
class BuscaCepScreenKtTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val testeDeUi = createAndroidComposeRule(MainActivity::class.java)
    private val uiDevice = UiDevice.getInstance(
        InstrumentationRegistry.getInstrumentation()
    )

    @Module
    @InstallIn(SingletonComponent::class)
    object FakeServiceModule {
        @Provides
        fun provideClient(): HttpClient {
            return fakeClient(mockEngineCerto)
        }
    }

    private fun digitaCep(cep: String) {
        testeDeUi.digitaNoCampoDeTexto(textoCampoTextoCep, cep)
    }

    private fun clicaNoBuscaEndereco() {
        testeDeUi.clicaNoElementoPeloNome(textoBotaoBuscarEndereco)
    }
    private fun digitaEPesquisaCep(cep: String){
        digitaCep(cep)
        clicaNoBuscaEndereco()
    }

    @Test
    fun deveMostarOsComponentesDaTela_QuandoAbrirOApp() {
        testeDeUi.verificaSeMostraOComponentePelaDescricao(descricaoImagemBuscaCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoBuscarEndereco)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoTextoCep)
    }

    @Test
    fun deveMostarOPlaceholder_QuandoClicarNoCampoDeTexto() {
        testeDeUi.clicaNoElementoPeloNome(textoCampoTextoCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoTextoCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(dicaCampoTextoCep)
    }
    @Test
    fun deveMostarOTextoDigitado_QuandoDigitarmosAlgoNoCampoDoCep(){
        digitaCep(textoComCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoComCepNaTela)
    }
    @Test
    fun dadoUmTextoDigitado_deveContinuarMostrandoOTexto_QuandoRotacionarmosATela(){
        digitaCep(textoComCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoComCepNaTela)
        uiDevice.rotacionarATela()
        testeDeUi.scrollaAteOElementoPeloNome(textoBotaoBuscarEndereco)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoComCepNaTela)
        uiDevice.voltarARotacaoDaTela()
    }
    @Test
    fun dadoUmTextoDigitado_deveContinuarMostrandoOTexto_QuandoFecharmosEReabrirmosOApp(){
        digitaCep(textoComCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoComCepNaTela)
        uiDevice.minimizarOAppEReabrir()
        testeDeUi.scrollaAteOElementoPeloNome(textoBotaoBuscarEndereco)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoComCepNaTela)
    }

    @Test
    fun deveAparecerMensagemDeCepObrigatorio_quandoClicarmosEmBuscaSemDigitar() {
        clicaNoBuscaEndereco()
        testeDeUi.verificaSeMostraOComponentePeloTexto(mensagemDeErroCampoObrigatorio)
    }

    @Test
    fun deveAparecerMensagemDeCepObrigatorio_quandoDigitarmosUmTextoEmBranco() {
        digitaEPesquisaCep(textoEmBranco)
        testeDeUi.verificaSeMostraOComponentePeloTexto(mensagemDeErroCampoObrigatorio)
    }

    @Test
    fun devemostraDialogDeErroDePreenchimento_quandoDigitamosMenosQue8Digitos() {
        digitaEPesquisaCep(textoCom7Digitos)
        testeDeUi.verificaSeMostraOComponentePeloTexto(mensagemDialogCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoVoltarDialog)
    }
    @Test
    fun deveAparecerNada_quandoColarmosUmTextoComMaisDe8Digitos() {
        digitaCep(textoCom9Digitos)
        testeDeUi.verificaSeNaoExisteOComponentePeloTexto(textoCom9Digitos)
        testeDeUi.verificaSeExisteOComponentPeloTexto(textoCampoTextoCep)
        testeDeUi.verificaSeExisteOComponentPeloTexto(dicaCampoTextoCep)
    }
    @Test
    fun dadoUmTextoCom8Digitos_deveIgnorarOCaracterExtra_quandoAdicionarmosUmDigitoAMais() {
        digitaCep(textoComCep)
        digitaCep(textoCom1Caractere)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoComCepNaTela)
        testeDeUi.verificaSeNaoExisteOComponentePeloTexto(textoCom9DigitosNaTela)
    }

    @Test
    fun dadoErroQueAbreDialog_deveFecharODialog_quandoClicarmosEmVoltar() {
        digitaEPesquisaCep(textoCom7Digitos)
        testeDeUi.verificaSeMostraOComponentePeloTexto(mensagemDialogCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoVoltarDialog)
        testeDeUi.clicaNoElementoPeloNome(textoBotaoVoltarDialog)
        testeDeUi.verificaSeNaoExisteOComponentePeloTexto(mensagemDialogCep)
        testeDeUi.verificaSeNaoExisteOComponentePeloTexto(textoBotaoVoltarDialog)
    }

    @Test
    fun dadoErroQueAbreDialog_deveFecharODialog_quandoClicarmosForaDele() {
        digitaEPesquisaCep(textoCom7Digitos)
        testeDeUi.verificaSeMostraOComponentePeloTexto(mensagemDialogCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoVoltarDialog)
        uiDevice.clicaNoCanto()
        testeDeUi.verificaSeNaoExisteOComponentePeloTexto(mensagemDialogCep)
        testeDeUi.verificaSeNaoExisteOComponentePeloTexto(textoBotaoVoltarDialog)
    }
    @Test
    fun deveAparecerDialogDeErro_quandoColocarmosTracoNoMeioDoCampoDeTexto() {
        digitaEPesquisaCep(textoComTraco)
        testeDeUi.verificaSeMostraOComponentePeloTexto(mensagemDialogCep)
    }
    @Test
    fun deveAparecerDialogDeErro_quandoColocarmosLetraNoCampoDeTexto() {
        digitaEPesquisaCep(textoComLetra)
        testeDeUi.verificaSeMostraOComponentePeloTexto(mensagemDialogCep)
    }

    @Test
    fun deveAparecerDialogDeErro_quandoColocarmosUmNumeroNaoInteiroNoCampoDeTexto() {
        digitaEPesquisaCep(textoComVirgula)
        testeDeUi.verificaSeMostraOComponentePeloTexto(mensagemDialogCep)
    }
    @Test
    fun deveAparecerDialogDeErro_quandoColocarmosUmNumeroNegativoNoCampoDeTexto() {
        digitaEPesquisaCep(textoComTracoNoInicio)
        testeDeUi.verificaSeMostraOComponentePeloTexto(mensagemDialogCep)
    }
    @Test
    fun deveIrParaATelaDeResultado_quandoInserirmosUmCepDe8Digitos() {
        digitaEPesquisaCep(textoComCep)
        testeDeUi.esperaAteATelaAparecer(textoBotaoCopiarTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoCopiarTelaSucesso)
    }


}