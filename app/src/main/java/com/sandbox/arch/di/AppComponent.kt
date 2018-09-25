package com.sandbox.arch.di

import android.content.Context
import com.sandbox.arch.ArchSandboxApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Valentyn on 28.12.2017.
 *
 */
@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(application: ArchSandboxApplication)

}
