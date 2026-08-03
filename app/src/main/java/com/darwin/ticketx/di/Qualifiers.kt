package com.darwin.ticketx.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PublicRetrofit


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit