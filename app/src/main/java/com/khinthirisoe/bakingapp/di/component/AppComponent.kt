package com.khinthirisoe.bakingapp.di.component

import android.content.Context
import com.khinthirisoe.bakingapp.data.network.BakingApiService
import com.khinthirisoe.bakingapp.di.App
import com.khinthirisoe.bakingapp.di.context.ApplicationContext
import com.khinthirisoe.bakingapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface AppComponent {

    @ApplicationContext
    fun getContext(): Context

    fun inject(application: App)

    fun app(): App

    fun apiService(): BakingApiService
}
