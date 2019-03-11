package com.khinthirisoe.bakingapp.di.module

import android.content.Context
import com.google.gson.Gson
import com.khinthirisoe.bakingapp.data.network.ApiService
import com.khinthirisoe.bakingapp.data.network.ApiUrl
import com.khinthirisoe.bakingapp.di.App
import com.khinthirisoe.bakingapp.di.context.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: App) {

    @Provides
    fun app(): App = app

    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun apiService(): ApiService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type", "application/json")

                val request = requestBuilder.build()
                chain.proceed(request)
            }
        client.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        return Retrofit.Builder().baseUrl(ApiUrl.BASE_URL)
            .client(client.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ApiService::class.java)
    }

}
