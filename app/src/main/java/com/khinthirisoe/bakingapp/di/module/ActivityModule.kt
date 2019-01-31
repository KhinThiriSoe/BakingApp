package com.khinthirisoe.bakingapp.di.module

import android.app.Activity
import android.content.Context
import com.khinthirisoe.bakingapp.di.context.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    fun activityContext(): Context = mActivity

    @Provides
    @ActivityContext
    fun provideActivityContext(): Context {
        return mActivity
    }
}