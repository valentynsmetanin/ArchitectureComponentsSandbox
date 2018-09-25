package com.sandbox.arch.di

import com.sandbox.arch.ArchSandboxApplication
import com.sandbox.arch.di.countries.CountriesComponent
import com.sandbox.arch.di.countries.CountriesModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Valentyn on 28.12.2017.
 *
 */
@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun inject(application: ArchSandboxApplication)

    fun plus(countriesModule: CountriesModule): CountriesComponent

}
