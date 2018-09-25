package com.sandbox.arch.di

import com.sandbox.arch.BuildConfig
import com.sandbox.arch.data.network.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Valentyn on 9/25/18.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    @NotNull
    fun providesHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)

        return client.build()
    }

    @Provides
    @Singleton
    @NotNull
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    @NotNull
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

}