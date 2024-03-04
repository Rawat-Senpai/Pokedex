package com.example.pokedex.di

import com.example.pokedex.api.PokemonApi
import com.example.pokedex.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {



    private var client = OkHttpClient().newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()


    @Singleton
    @Provides
    fun providesRetrofit():Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(client)

    }


    @Singleton
    @Provides
    fun providesUserApi(retrofitBuilder: Retrofit.Builder):PokemonApi{
        return retrofitBuilder.client(client).build().create(PokemonApi::class.java)
    }


}