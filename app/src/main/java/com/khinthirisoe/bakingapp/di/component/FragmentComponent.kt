package com.khinthirisoe.bakingapp.di.component

import android.content.Context
import com.khinthirisoe.bakingapp.di.context.ActivityContext
import com.khinthirisoe.bakingapp.di.module.FragmentModule
import com.khinthirisoe.bakingapp.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(FragmentModule::class)])
interface FragmentComponent {

    @get:ActivityContext
    var context: Context

}