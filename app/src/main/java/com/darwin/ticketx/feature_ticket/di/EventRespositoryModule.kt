package com.darwin.ticketx.feature_ticket.di

import com.darwin.ticketx.feature_ticket.data.repository.EventRepositoryImpl
import com.darwin.ticketx.feature_ticket.domain.repository.EventRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EventRespositoryModule {
    @Binds
    @Singleton
    abstract fun bindEventRepository(
        impl: EventRepositoryImpl
    ): EventRepository
}