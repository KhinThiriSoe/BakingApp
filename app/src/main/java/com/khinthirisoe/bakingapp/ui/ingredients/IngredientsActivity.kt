package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.model.Ingredient
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.steps.StepsActivity


class IngredientsActivity : AppCompatActivity(), StepsAdapter.StepRecyclerViewClickListener {

    private lateinit var ingredientRecyclerView: RecyclerView
    private var ingredientsAdapter: IngredientsAdapter? = null
    private lateinit var stepRecyclerView: RecyclerView
    private var stepsAdapter: StepsAdapter? = null

    private lateinit var bakingName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val bakingRecipe = intent.getParcelableExtra<Recipe>("baking")
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

        val mIngredientLayoutManager = LinearLayoutManager(this)
        ingredientRecyclerView.layoutManager = mIngredientLayoutManager

        val mLayoutManager = LinearLayoutManager(this)
        stepRecyclerView.layoutManager = mLayoutManager
    }

    private fun configureUI(recipe: Recipe) {
        ingredientsAdapter = IngredientsAdapter(this, recipe.ingredients as MutableList<Ingredient>)
        ingredientRecyclerView.adapter = ingredientsAdapter

        stepsAdapter = StepsAdapter(this, recipe.steps as MutableList<Step>, this)
        stepRecyclerView.adapter = stepsAdapter
    }

    override fun listItemClick(step: Step) {
        startActivity(Intent(this, StepsActivity::class.java).putExtra("step", step))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
