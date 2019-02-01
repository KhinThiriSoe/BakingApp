package com.khinthirisoe.bakingapp.di.component

import android.content.Context
import com.khinthirisoe.bakingapp.di.context.ApplicationContext
import com.khinthirisoe.bakingapp.di.module.ActivityModule
import com.khinthirisoe.bakingapp.di.scope.ActivityScope
import com.khinthirisoe.bakingapp.ui.receipes.ReceipesContract
import com.khinthirisoe.bakingapp.ui.receipes.model.ReceipesInteractor
import com.khinthirisoe.bakingapp.ui.receipes.view.ReceipesActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [ActivityModule::class])
interface ActivityComponent : AppComponent {

    @ApplicationContext
    override fun getContext(): Context

    fun receipeInteractor(): ReceipesInteractor

    fun receipePresenter(): ReceipesContract.Presenter

    fun inject(activity: ReceipesActivity)

}