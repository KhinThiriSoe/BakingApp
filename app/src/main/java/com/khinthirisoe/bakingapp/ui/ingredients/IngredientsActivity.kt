package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.steps.StepsActivity


class IngredientsActivity : AppCompatActivity(), StepsAdapter.StepRecyclerViewClickListener {

    companion object {
        const val EXTRA_BAKING = "extra_baking"
    }

    private lateinit var ingredientRecyclerView: RecyclerView
    private var ingredientsAdapter: IngredientsAdapter? = null
    private lateinit var stepRecyclerView: RecyclerView
    private var stepsAdapter: StepsAdapter? = null

    private lateinit var bakingName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val bakingRecipe = intent.getParcelableExtra<Recipe>(EXTRA_BAKING)
        bakingName = bakingRecipe.name

        setUpToolbar()

        setUpView()

        configureUI(bakingRecipe)
    }

    private fun setUpToolbar() {
        if (supportActionBar != null) {
            supportActionBar?.title = bakingName
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpView() {

        ingredientRecyclerView = findViewById(R.id.ingredient_recyclerView)
        stepRecyclerView = findViewById(R.id.step_recyclerView)

        val ingredientLayoutManager = LinearLayoutManager(this)
        ingredientRecyclerView.layoutManager = ingredientLayoutManager

        val stepLayoutManager = LinearLayoutManager(this)
        stepRecyclerView.layoutManager = stepLayoutManager

        ingredientsAdapter = IngredientsAdapter(null)
        stepsAdapter = StepsAdapter(null, this)

    }

    private fun configureUI(recipe: Recipe) {

        ingredientsAdapter?.setIngredients(recipe.ingredients)
        ingredientRecyclerView.adapter = ingredientsAdapter

        stepsAdapter?.setSteps(recipe.steps)
        stepRecyclerView.adapter = stepsAdapter
    }

    override fun listItemClick(step: Step) {
        startActivity(Intent(this, StepsActivity::class.java).putExtra(StepsActivity.EXTRA_STEP, step))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
