package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.data.model.Ingredient
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.steps.view.StepsActivity




class IngredientsActivity : AppCompatActivity(), StepsAdapter.StepRecyclerViewClickListener {

    companion object {
        const val EXTRA_BAKING = "extra_baking"
    }

    private lateinit var ingredientRecyclerView: RecyclerView
    private var ingredientsAdapter: IngredientsAdapter? = null
    private lateinit var stepRecyclerView: RecyclerView
    private var stepsAdapter: StepsAdapter? = null

    private lateinit var bakingName: String
    private var stepList: MutableList<Step>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.khinthirisoe.bakingapp.R.layout.activity_ingredients)

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

        ingredientRecyclerView = findViewById(com.khinthirisoe.bakingapp.R.id.ingredient_recyclerView)
        stepRecyclerView = findViewById(com.khinthirisoe.bakingapp.R.id.step_recyclerView)

        val mIngredientLayoutManager = LinearLayoutManager(this)
        ingredientRecyclerView.layoutManager = mIngredientLayoutManager

        val mLayoutManager = LinearLayoutManager(this)
        stepRecyclerView.addItemDecoration(DividerItemDecoration(stepRecyclerView.context, DividerItemDecoration.VERTICAL))
        stepRecyclerView.layoutManager = mLayoutManager
    }

    private fun configureUI(recipe: Recipe) {
        stepList = recipe.steps

        ingredientsAdapter = IngredientsAdapter(this, recipe.ingredients as MutableList<Ingredient>)
        ingredientRecyclerView.adapter = ingredientsAdapter

        stepsAdapter = StepsAdapter(recipe.steps as MutableList<Step>, this)
        stepRecyclerView.adapter = stepsAdapter
    }

    override fun listItemClick(step: Step) {
        startActivity(
            Intent(this, StepsActivity::class.java)
                .putExtra(StepsActivity.EXTRA_STEP, step)
                .putParcelableArrayListExtra(StepsActivity.EXTRA_STEP_LIST, (ArrayList(stepList)))
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
