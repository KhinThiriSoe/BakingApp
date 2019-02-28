package com.khinthirisoe.bakingapp.ui.ingredients

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.steps.StepsActivity
import kotlinx.android.synthetic.main.activity_ingredients.*


class IngredientsActivity : AppCompatActivity(), StepsAdapter.StepRecyclerViewClickListener {

    companion object {
        const val EXTRA_BAKING = "extra_baking"
    }

    private var ingredientsAdapter: IngredientsAdapter? = null
    private var stepsAdapter: StepsAdapter? = null
    private var bakingRecipe: Recipe? = null

    private lateinit var bakingName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        bakingRecipe = intent.getParcelableExtra<Recipe>(EXTRA_BAKING)

        initView()

    }

    private fun initView() {

        setUpToolbar()

        setupIngredients()

        setUpSteps()

    }

    private fun setUpToolbar() {
        if (supportActionBar != null) {
            supportActionBar?.title = bakingRecipe!!.name
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    private fun setupIngredients() {
        val ingredientLayoutManager = LinearLayoutManager(this)
        ingredient_recyclerView.layoutManager = ingredientLayoutManager

        ingredientsAdapter = IngredientsAdapter(null)

        ingredientsAdapter?.setIngredients(bakingRecipe!!.ingredients)
        ingredient_recyclerView.adapter = ingredientsAdapter

    }

    private fun setUpSteps() {

        val stepLayoutManager = LinearLayoutManager(this)
        step_recyclerView.layoutManager = stepLayoutManager

        stepsAdapter = StepsAdapter(null, this)


        stepsAdapter?.setSteps(bakingRecipe!!.steps)
        step_recyclerView.adapter = stepsAdapter
    }

    override fun listItemClick(step: Step) {
        startActivity(StepsActivity.createIntent(this, step))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
