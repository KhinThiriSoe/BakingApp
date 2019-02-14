package com.khinthirisoe.bakingapp.ui.baking.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.di.component.AppComponent
import com.khinthirisoe.bakingapp.di.component.DaggerActivityComponent
import com.khinthirisoe.bakingapp.di.module.ActivityModule
import com.khinthirisoe.bakingapp.ui.baking.BakingContract
import com.khinthirisoe.bakingapp.ui.base.BaseActivity
import com.khinthirisoe.bakingapp.ui.ingredients.IngredientsActivity
import javax.inject.Inject

class BakingActivity : BaseActivity(), BakingContract.View, BakingAdapter.BakingRecyclerViewClickListener {

    @Inject
    lateinit var presenter: BakingContract.Presenter

    private lateinit var bakingRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var bakingAdapter: BakingAdapter? = null

    override fun setupComponent(appComponent: AppComponent) {
        val component =
            DaggerActivityComponent.builder().appComponent(appComponent).activityModule(ActivityModule(this)).build()
        component.inject(this)

        presenter.onAttachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.khinthirisoe.bakingapp.R.layout.activity_baking)

        initView()

        presenter.fetchBakingRecipes()
    }

    private fun initView() {

        bakingRecyclerView = findViewById(com.khinthirisoe.bakingapp.R.id.recyclerView)
        progressBar = findViewById(com.khinthirisoe.bakingapp.R.id.progressBar)

        val layoutManager =
            GridLayoutManager(this, resources.getInteger(R.integer.number_of_grid_items))

        bakingRecyclerView.layoutManager = layoutManager

    }

    override fun showBakingLists(list: ArrayList<Recipe>) {
        bakingAdapter = BakingAdapter(this, list as MutableList<Recipe>, this)
        bakingRecyclerView.adapter = bakingAdapter
    }

    override fun listItemClick(baking: Recipe) {
        startActivity(
            Intent(this, IngredientsActivity::class.java).putExtra(
                IngredientsActivity.EXTRA_BAKING,
                baking as Parcelable
            )
        )
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}
