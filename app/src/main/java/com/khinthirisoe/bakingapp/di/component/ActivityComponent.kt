package com.khinthirisoe.bakingapp.di.component

import android.content.Context
import com.khinthirisoe.bakingapp.di.context.ApplicationContext
import com.khinthirisoe.bakingapp.di.module.ActivityModule
import com.khinthirisoe.bakingapp.di.scope.ActivityScope
import com.khinthirisoe.bakingapp.ui.baking.BakingContract
import com.khinthirisoe.bakingapp.ui.baking.model.BakingRepository
import com.khinthirisoe.bakingapp.ui.baking.view.BakingActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [ActivityModule::class])
interface ActivityComponent : AppComponent {

    @ApplicationContext
    override fun getContext(): Context

    fun bakingRepository(): BakingRepository

    fun bakingPresenter(): BakingContract.Presenter

    fun inject(activity: BakingActivity)

}