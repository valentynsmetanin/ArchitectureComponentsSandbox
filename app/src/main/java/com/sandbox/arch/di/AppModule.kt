package com.sandbox.arch.di

import com.sandbox.arch.di.main.ForMain
import com.sandbox.arch.di.main.MainActivityModule
import com.sandbox.arch.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Valentyn on 10/17/17.
 */
@Module
interface AppModule {

    @ForMain
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun baseActivityInjector(): MainActivity

}