package com.sandbox.arch.di

import android.content.Context
import com.sandbox.arch.ArchSandboxApplication
import com.sandbox.arch.network.Api
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Valentyn on 28.12.2017.
 *
 */
@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun context(): Context
    fun application(): ArchSandboxApplication
    fun api(): Api

    fun inject(application: ArchSandboxApplication)

    // TODO fun plus(countriesModule: CountriesModule): CountriesComponent

}
