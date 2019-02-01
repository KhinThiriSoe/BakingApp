package com.khinthirisoe.bakingapp.di.module

import android.app.Activity
import android.content.Context
import com.khinthirisoe.bakingapp.data.network.ApiHelper
import com.khinthirisoe.bakingapp.di.context.ActivityContext
import com.khinthirisoe.bakingapp.ui.receipes.ReceipesContract
import com.khinthirisoe.bakingapp.ui.receipes.model.ReceipesInteractor
import com.khinthirisoe.bakingapp.ui.receipes.presenter.ReceipesPresenter
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

    @Provides
    fun receipeInteractor(apiHelper: ApiHelper): ReceipesInteractor = ReceipesInteractor(apiHelper)

    @Provides
    fun receipePresenter(interactor: ReceipesInteractor): ReceipesContract.Presenter = ReceipesPresenter(interactor)
}