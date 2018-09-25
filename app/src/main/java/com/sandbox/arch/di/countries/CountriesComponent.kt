package com.sandbox.arch.di.countries

import com.sandbox.arch.presentation.countries.CountriesFragment
import com.sandbox.arch.presentation.countries.CountriesViewModelFactory
import dagger.Subcomponent

/**
 * Created by Valentyn on 9/25/18.
 */
@Subcomponent(modules = [CountriesModule::class])
@ForCountries
interface CountriesComponent {

    fun inject(fragment: CountriesFragment)

    fun viewModelCountriesFactory(): CountriesViewModelFactory

}