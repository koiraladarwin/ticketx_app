package com.darwin.ticketx.core.network

import com.darwin.ticketx.di.PublicRetrofit
import com.darwin.ticketx.feature_auth.data.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PublicNetworkModule {


    @Provides
    @Singleton
    @PublicRetrofit
    fun providePublicRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(
                NetworkConstants.BASE_URL
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }



}