package com.khinthirisoe.bakingapp.ui.receipes.view

import android.os.Bundle
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.ReceipeResponse
import com.khinthirisoe.bakingapp.di.component.AppComponent
import com.khinthirisoe.bakingapp.di.component.DaggerActivityComponent
import com.khinthirisoe.bakingapp.di.module.ActivityModule
import com.khinthirisoe.bakingapp.ui.base.BaseActivity
import com.khinthirisoe.bakingapp.ui.receipes.ReceipesContract
import javax.inject.Inject

class ReceipesActivity : BaseActivity(), ReceipesContract.View {

    @Inject
    lateinit var presenter: ReceipesContract.Presenter

    override fun setupComponent(appComponent: AppComponent) {
        val component = DaggerActivityComponent.builder().appComponent(appComponent).activityModule(ActivityModule(this)).build()
        component.inject(this)

        presenter.onAttachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipes)

        presenter.fetchReceipes()

    }

    override fun showReceipes(receipes: ReceipeResponse) {
    }

    override fun showMessage(message: String) {
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}
