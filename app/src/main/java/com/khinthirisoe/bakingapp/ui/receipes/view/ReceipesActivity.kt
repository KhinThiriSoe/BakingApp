package com.khinthirisoe.bakingapp.ui.receipes.view

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private lateinit var mRecyclerView: RecyclerView
    private var mAdapter: ReceipesAdapter? = null

    override fun setupComponent(appComponent: AppComponent) {
        val component =
            DaggerActivityComponent.builder().appComponent(appComponent).activityModule(ActivityModule(this)).build()
        component.inject(this)

        presenter.onAttachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.khinthirisoe.bakingapp.R.layout.activity_receipes)

        presenter.fetchReceipes()

        mRecyclerView = findViewById(com.khinthirisoe.bakingapp.R.id.recyclerView)

        val mLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = mLayoutManager

    }

    override fun showReceipes(receipes: ArrayList<ReceipeResponse>) {

        mAdapter = ReceipesAdapter(this, receipes)
        mRecyclerView.adapter = mAdapter
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}
