package com.sandbox.arch.di

import android.content.Context
import com.sandbox.arch.ArchSandboxApplication
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton


/**
 * Created by Valentyn on 10/17/17.
 */
@Module
class AppModule(private val app: ArchSandboxApplication) {

    @Provides
    @NotNull
    @Singleton
    fun providesContext(): Context = app

    @Provides
    @NotNull
    @Singleton
    fun providesApplication(): ArchSandboxApplication = app

}