package com.sandbox.arch.di

import com.sandbox.arch.data.CountriesRepository
import com.sandbox.arch.data.network.Api
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton


/**
 * Created by Valentyn on 9/25/18.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    @NotNull
    fun provideCountriesRepository(api: Api) = CountriesRepository(api)

}