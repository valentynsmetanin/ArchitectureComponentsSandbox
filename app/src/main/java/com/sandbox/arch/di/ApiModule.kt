package com.sandbox.arch.di

import com.sandbox.arch.network.Api
import com.sandbox.arch.network.ApiClient
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton


/**
 * Created by Valentyn on 10/17/17.
 */
@Module
class ApiModule {

    @Provides
    @NotNull
    @Singleton
    fun providesApiClient(): Api = ApiClient().createService()

}