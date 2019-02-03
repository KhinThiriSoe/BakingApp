package com.khinthirisoe.bakingapp.ui.receipes.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.ReceipeResponse
import com.khinthirisoe.bakingapp.di.component.AppComponent
import com.khinthirisoe.bakingapp.di.component.DaggerActivityComponent
import com.khinthirisoe.bakingapp.di.module.ActivityModule
import com.khinthirisoe.bakingapp.ui.base.BaseActivity
import com.khinthirisoe.bakingapp.ui.ingredients.IngredientsActivity
import com.khinthirisoe.bakingapp.ui.receipes.ReceipesContract
import javax.inject.Inject


class ReceipesActivity : BaseActivity(), ReceipesContract.View, ReceipesAdapter.ReceipeRecyclerViewClickListener {

    @Inject
    lateinit var presenter: ReceipesContract.Presenter

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mProgressBar: ProgressBar
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

        mRecyclerView = findViewById(R.id.recyclerView)
        mProgressBar = findViewById(R.id.progressBar)

        presenter.fetchReceipes()

        val mLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = mLayoutManager

    }

    override fun showReceipes(receipes: ArrayList<ReceipeResponse>) {

        mAdapter = ReceipesAdapter(this, receipes as MutableList<ReceipeResponse>, this)
        mRecyclerView.adapter = mAdapter
    }

    override fun listItemClick(list: ReceipeResponse) {
        val forward = Intent(this, IngredientsActivity::class.java)
        forward.putExtra("data", list)
        startActivity(forward)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mProgressBar.visibility = View.GONE
    }
}
