package com.darwin.ticketx.feature_ticket.di

import com.darwin.ticketx.di.AuthTicketRetrofit
import com.darwin.ticketx.feature_ticket.data.api.EventApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object EvenApiModule {

    @Provides
    @Singleton
    fun provideEventApi(
        @AuthTicketRetrofit retrofit: Retrofit
    ): EventApi {
        return retrofit.create(EventApi::class.java)
    }

}