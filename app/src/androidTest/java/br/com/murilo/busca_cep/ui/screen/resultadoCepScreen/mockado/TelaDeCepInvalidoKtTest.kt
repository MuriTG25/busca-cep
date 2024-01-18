package br.com.murilo.busca_cep.ui.screen.resultadoCepScreen.mockado

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import br.com.murilo.busca_cep.MainActivity
import br.com.murilo.busca_cep.extras.clicaNoElementoPeloNome
import br.com.murilo.busca_cep.extras.digitaNoCampoDeTexto
import br.com.murilo.busca_cep.extras.esperaAteATelaAparecer
import br.com.murilo.busca_cep.extras.fakeClient
import br.com.murilo.busca_cep.extras.mockEngineCepErro
import br.com.murilo.busca_cep.extras.mockEngineErroConexao
import br.com.murilo.busca_cep.extras.textoBotaoBuscarEndereco
import br.com.murilo.busca_cep.extras.textoBotaoVoltarTelaCepInvalido
import br.com.murilo.busca_cep.extras.textoCampoTextoCep
import br.com.murilo.busca_cep.extras.textoComCep
import br.com.murilo.busca_cep.extras.textoFalhaTelaCepInvalido
import br.com.murilo.busca_cep.extras.textoFalhaTelaErro
import br.com.murilo.busca_cep.extras.verificaSeExisteOComponentPeloTexto
import br.com.murilo.busca_cep.extras.verificaSeMostraOComponentePeloTexto
import br.com.murilo.busca_cep.extras.verificaSeOElementoEClicavelPeloTexto
import br.com.murilo.busca_cep.infraestrutura.di.module.RestApiModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import org.junit.Rule
import org.junit.Test

@UninstallModules(RestApiModule::class)
@HiltAndroidTest
class TelaDeCepInvalidoKtTest {
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
            return fakeClient(mockEngineCepErro)
        }
    }
    private fun vaiParaAtelaDeResultados(){
        testeDeUi.digitaNoCampoDeTexto(textoCampoTextoCep, textoComCep)
        testeDeUi.clicaNoElementoPeloNome(textoBotaoBuscarEndereco)
        testeDeUi.esperaAteATelaAparecer(textoFalhaTelaCepInvalido)
    }
    @Test
    fun deveMostarMensagemDeErroEBotaoDeVolta_quandoFormosParaATelaDeCepInvalido(){
        vaiParaAtelaDeResultados()
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoFalhaTelaCepInvalido)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoVoltarTelaCepInvalido)
        testeDeUi.verificaSeOElementoEClicavelPeloTexto(textoBotaoVoltarTelaCepInvalido)
    }
    @Test
    fun deveVoltarParaATelaDeBusca_quandoClicarmosNoBotaoDeVoltar(){
        vaiParaAtelaDeResultados()
        testeDeUi.clicaNoElementoPeloNome(textoBotaoVoltarTelaCepInvalido)
        testeDeUi.esperaAteATelaAparecer(textoBotaoBuscarEndereco)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoTextoCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoBuscarEndereco)
    }
}