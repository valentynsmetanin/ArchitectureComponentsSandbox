package com.sandbox.arch.di.main

import com.sandbox.arch.di.countries.CountriesModule
import com.sandbox.arch.di.countries.ForCountries
import com.sandbox.arch.presentation.countries.CountriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Valentyn on 9/25/18.
 */
@Module
interface MainActivityModule {

    @ForCountries
    @ContributesAndroidInjector(modules = [ CountriesModule::class] )
    fun countriesFragment(): CountriesFragment

}