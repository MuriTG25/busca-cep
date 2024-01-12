package br.com.murilo.busca_cep.infraestrutura.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

private const val hostLink = "viacep.com.br"
private const val pathLink = "ws/"
@Module
@InstallIn(SingletonComponent::class)
class RestApiModule {
    @Singleton
    @Provides
    fun provideClient(): HttpClient {
        return HttpClient(CIO) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = hostLink
                    path(pathLink)
                }
            }
        }
    }
}