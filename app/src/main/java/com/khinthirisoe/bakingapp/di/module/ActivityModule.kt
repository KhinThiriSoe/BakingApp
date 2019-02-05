package com.khinthirisoe.bakingapp.di.module

import android.app.Activity
import android.content.Context
import com.khinthirisoe.bakingapp.data.network.ApiHelper
import com.khinthirisoe.bakingapp.di.context.ActivityContext
import com.khinthirisoe.bakingapp.ui.baking.BakingContract
import com.khinthirisoe.bakingapp.ui.baking.model.BakingRepository
import com.khinthirisoe.bakingapp.ui.baking.presenter.BakingPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun activityContext(): Context = activity

    @Provides
    @ActivityContext
    fun provideActivityContext(): Context {
        return activity
    }

    @Provides
    fun bakingRepository(apiHelper: ApiHelper): BakingRepository = BakingRepository(apiHelper)

    @Provides
    fun bakingPresenter(repository: BakingRepository): BakingContract.Presenter = BakingPresenter(repository)
}