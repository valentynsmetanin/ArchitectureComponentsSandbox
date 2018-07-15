package com.sandbox.arch

import android.app.Application
import com.sandbox.arch.di.AppComponent
import com.sandbox.arch.di.AppModule
import com.sandbox.arch.di.DaggerAppComponent

class ArchSandboxApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

}