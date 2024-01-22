package br.com.murilo.busca_cep.ui.screen.resultadoCepScreen.mockado

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import br.com.murilo.busca_cep.MainActivity
import br.com.murilo.busca_cep.extras.clicaNoElementoPeloNome
import br.com.murilo.busca_cep.extras.digitaNoCampoDeTexto
import br.com.murilo.busca_cep.extras.esperaAteATelaAparecer
import br.com.murilo.busca_cep.extras.fakeClient
import br.com.murilo.busca_cep.extras.mockEngineCerto
import br.com.murilo.busca_cep.extras.textoBairroSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoBotaoBuscarEndereco
import br.com.murilo.busca_cep.extras.textoBotaoCopiarTelaSucesso
import br.com.murilo.busca_cep.extras.textoBotaoVoltarTelaCepInvalido
import br.com.murilo.busca_cep.extras.textoCampoBairroTelaSucesso
import br.com.murilo.busca_cep.extras.textoCampoCepTelaSucesso
import br.com.murilo.busca_cep.extras.textoCampoCidadeTelaSucesso
import br.com.murilo.busca_cep.extras.textoCampoComplementoTelaSucesso
import br.com.murilo.busca_cep.extras.textoCampoEstadoTelaSucesso
import br.com.murilo.busca_cep.extras.textoCampoLogradouroTelaSucesso
import br.com.murilo.busca_cep.extras.textoCampoTextoCep
import br.com.murilo.busca_cep.extras.textoCepSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoCidadeSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoComCep
import br.com.murilo.busca_cep.extras.textoComplementoSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoEstadoSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoLogradouroSeTelaSucesso
import br.com.murilo.busca_cep.extras.textoNoClipboard
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
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test

@UninstallModules(RestApiModule::class)
@HiltAndroidTest
class TelaDeSucessoKtTest {
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
    private fun vaiParaAtelaDeResultados(){
        testeDeUi.digitaNoCampoDeTexto(textoCampoTextoCep, textoComCep)
        testeDeUi.clicaNoElementoPeloNome(textoBotaoBuscarEndereco)
        testeDeUi.esperaAteATelaAparecer(textoBotaoCopiarTelaSucesso)
    }
    @Test
    fun deveAparecerOsCamposDeTextoEBotaoDeCopiar_quandoBuscarUmCepValido(){
        vaiParaAtelaDeResultados()
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoCepTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoLogradouroTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoComplementoTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoBairroTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoCidadeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoEstadoTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoCopiarTelaSucesso)
        testeDeUi.verificaSeOElementoEClicavelPeloTexto(textoBotaoCopiarTelaSucesso)
    }
    @Test
    fun deveMostarOsValoresDoEndereco_quandoBuscarUmCepValido(){
        vaiParaAtelaDeResultados()
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCepSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoLogradouroSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoComplementoSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBairroSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCidadeSeTelaSucesso)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoEstadoSeTelaSucesso)
    }
    @Test
    fun deveVoltarParaATelaDeBusca_quandoClicarmosNoBotaoDeVoltar(){
        vaiParaAtelaDeResultados()
        testeDeUi.clicaNoElementoPeloNome(textoBotaoVoltarTelaCepInvalido)
        testeDeUi.esperaAteATelaAparecer(textoBotaoBuscarEndereco)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoCampoTextoCep)
        testeDeUi.verificaSeMostraOComponentePeloTexto(textoBotaoBuscarEndereco)
    }
    //TODO preciso saber como fazer teste para saber se o elemento é copiavel
    //TODO preciso saber como verificar clipboard
}