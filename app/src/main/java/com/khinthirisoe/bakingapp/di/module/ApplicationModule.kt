package com.khinthirisoe.bakingapp.di.module

import android.content.Context
import com.khinthirisoe.bakingapp.di.App
import com.khinthirisoe.bakingapp.di.context.ApplicationContext
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule(private val app: App) {

    @Provides
    fun app(): App = app

    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return app
    }
}
