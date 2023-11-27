package br.com.alura.desafio_cep.infraestrutura.di.module

import br.com.alura.desafio_cep.infraestrutura.network.services.CepBuscaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val urlBase = "https://viacep.com.br/ws/"

@Module
@InstallIn(SingletonComponent::class)
object RestApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideCepService(retrofit: Retrofit):CepBuscaService{
        return retrofit.create(CepBuscaService::class.java)
    }
}