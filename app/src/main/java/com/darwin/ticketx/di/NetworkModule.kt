package com.darwin.ticketx.core.network


import com.darwin.ticketx.di.AuthRetrofit
import com.darwin.ticketx.di.PublicRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp(
        interceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {


        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .authenticator(authAuthenticator)
            .build()

    }

    @Provides
    @Singleton
    @AuthRetrofit
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(
                NetworkConstants.BASE_URL
            )
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

}
@Module
@InstallIn(SingletonComponent::class)
object AuthNetworkModule {

    @Provides
    @Singleton
    fun provideRefreshApi(
        @PublicRetrofit retrofit: Retrofit
    ): RefreshApi {
        return retrofit.create(RefreshApi::class.java)
    }
}