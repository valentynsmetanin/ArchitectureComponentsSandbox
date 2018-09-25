package com.sandbox.arch.di.countries

import com.sandbox.arch.data.network.Api
import com.sandbox.arch.data.CountriesRepository
import com.sandbox.arch.presentation.countries.CountriesViewModelFactory
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull

/**
 * Created by Valentyn on 9/25/18.
 */
@Module
class CountriesModule {

    @Provides
    @NotNull
    @ForCountries
    fun providesCountriesRepository(api: Api): CountriesRepository
            = CountriesRepository(api)

    @Provides
    @NotNull
    @ForCountries
    fun providesCountriesViewModelFactory(countriesRepository: CountriesRepository): CountriesViewModelFactory
            = CountriesViewModelFactory(countriesRepository)

}