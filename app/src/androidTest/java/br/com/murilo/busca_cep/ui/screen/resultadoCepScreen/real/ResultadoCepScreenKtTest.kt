package br.com.murilo.busca_cep.ui.screen.resultadoCepScreen.real

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import br.com.murilo.busca_cep.MainActivity
import br.com.murilo.busca_cep.extras.clicaNoElementoPeloNome
import br.com.murilo.busca_cep.extras.descricaoTelaCarregamento
import br.com.murilo.busca_cep.extras.digitaNoCampoDeTexto
import br.com.murilo.busca_cep.extras.esperaAteATelaAparecer
import br.com.murilo.busca_cep.extras.esperaAteATelaAparecerComTempo
import br.com.murilo.busca_cep.extras.esperaAteATelaAparecerPelaDescricao
import br.com.murilo.busca_cep.extras.textoBairroSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoBotaoBuscarEndereco
import br.com.murilo.busca_cep.extras.textoBotaoCopiarTelaSucesso
import br.com.murilo.busca_cep.extras.textoBotaoRecarregarTelaFalha
import br.com.murilo.busca_cep.extras.textoBotaoVoltarTelaCepInvalido
import br.com.murilo.busca_cep.extras.textoBotaoVoltarTelaFalha
import br.com.murilo.busca_cep.extras.textoCampoTextoCep
import br.com.murilo.busca_cep.extras.textoCepSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoCidadeSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoComplementoSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoEstadoSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoFalhaTelaCepInvalido
import br.com.murilo.busca_cep.extras.textoFalhaTelaErro
import br.com.murilo.busca_cep.extras.textoLogradouroSeTelaSucesso
import br.com.murilo.busca_cep.extras.verificaSeExisteOComponentPeloTexto
import br.com.murilo.busca_cep.extras.verificaSeMostraOComponentePelaDescricao
import br.com.murilo.busca_cep.extras.verificaSeMostraOComponentePeloTexto
import br.com.murilo.busca_cep.extras.verificaSeOElementoEClicavelPeloTexto
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ResultadoCepScreenKtTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val testeDeUi = createAndroidComposeRule(MainActivity::class.java)
    private val uiDevice = UiDevice.getInstance(
        InstrumentationRegistry.getInstrumentation()
    )
    private fun vaiParaAtelaDeResultados(
        cep:String,
        textoDaTelaDeResultado: String,
    ){
        testeDeUi.digitaNoCampoDeTexto(textoCampoTextoCep, cep)
        testeDeUi.clicaNoElementoPeloNome(textoBotaoBuscarEndereco)
        testeDeUi.esperaAteATelaAparecerComTempo(textoDaTelaDeResultado)
    }
    private val cepSe = "01001000"
    private val cepInvalido = "99999999"
    @Test
    fun deveMostrarOsDadosDoCep_quandoInserirUmCepValido(){
        vaiParaAtelaDeResultados(cepSe, textoBotaoCopiarTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCepSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoLogradouroSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoComplementoSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBairroSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCidadeSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoEstadoSeTelaSucesso)
    }
    @Test
    fun deveMostarATelaDeCepInvalido_quandoInserirUmCepInvalido(){
        vaiParaAtelaDeResultados(cepInvalido, textoFalhaTelaCepInvalido)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoFalhaTelaCepInvalido)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoVoltarTelaCepInvalido)
    }
    @Test
    fun deveMostarATelaDeCarregamento_quandoPesquisarUmCep(){
        testeDeUi.digitaNoCampoDeTexto(textoCampoTextoCep, cepInvalido)
        testeDeUi.clicaNoElementoPeloNome(textoBotaoBuscarEndereco)
        testeDeUi.esperaAteATelaAparecerPelaDescricao(descricaoTelaCarregamento)
        testeDeUi.verificaSeMostraOComponentePelaDescricao(descricaoTelaCarregamento)
    }
    //TODO preciso de um jeito de automatizar o desligamento do wifi e da internet
    @Test
    fun dadaAConexaoOffline_deveMostarATelaDeFalha_quandoPesquisarUmCep(){
        vaiParaAtelaDeResultados(cepInvalido, textoFalhaTelaErro)
        testeDeUi.verificaSeExisteOComponentPeloTexto(textoBotaoRecarregarTelaFalha)
        testeDeUi.verificaSeExisteOComponentPeloTexto(textoBotaoVoltarTelaFalha)
    }
}