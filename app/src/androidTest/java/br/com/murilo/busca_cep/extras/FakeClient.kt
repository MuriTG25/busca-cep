package br.com.murilo.busca_cep.extras

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json

fun fakeClient(engine: HttpClientEngine):HttpClient{
    return HttpClient(engine){
        install(ContentNegotiation){
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}
private fun mocKEngineFactory(
    conteudo: String = "",
    httpStatusCode: HttpStatusCode = HttpStatusCode.OK
):MockEngine {
    return MockEngine {
        respond(
            content = ByteReadChannel(
                conteudo
            ),
            status = httpStatusCode,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }
}
val mockEngineCerto = mocKEngineFactory(
    conteudo = """{
      "cep": "01001-000",
      "logradouro": "Praça da Sé",
      "complemento": "lado ímpar",
      "bairro": "Sé",
      "localidade": "São Paulo",
      "uf": "SP",
      "ibge": "3550308",
      "gia": "1004",
      "ddd": "11",
      "siafi": "7107"
    }""".trimIndent(),
)
val mockEngineCepErro = mocKEngineFactory(
    conteudo = """{
      "erro": "true"
    }""".trimIndent(),
)
val mockEngineErroConexao = mocKEngineFactory(
    httpStatusCode = HttpStatusCode.BadRequest
)