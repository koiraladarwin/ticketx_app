package com.darwin.ticketx.feature_auth.di

import com.darwin.ticketx.di.AuthRetrofit
import com.darwin.ticketx.di.PublicRetrofit
import com.darwin.ticketx.feature_auth.data.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthNetworkModule {

    @Provides
    @Singleton
    fun provideAuthApi(
        @AuthRetrofit retrofit: Retrofit
    ): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}