package com.khinthirisoe.bakingapp.ui.ingredients

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Ingredient
import com.khinthirisoe.bakingapp.data.model.ReceipeResponse
import com.khinthirisoe.bakingapp.data.model.Step


class IngredientsActivity : AppCompatActivity() {

    private lateinit var mIngredientRecyclerView: RecyclerView
    private var mIngreditentsAdapter: IngreditentsAdapter? = null
    private lateinit var mStepRecyclerView: RecyclerView
    private var mStepsAdapter: StepsAdapter? = null


    private lateinit var receipeName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val data = intent.getParcelableExtra<ReceipeResponse>("data")
        receipeName = data.name

        setUpToolbar()

        mIngredientRecyclerView = findViewById(R.id.ingredient_recyclerView)
        mStepRecyclerView = findViewById(R.id.step_recyclerView)

        val mIngredientLayoutManager = LinearLayoutManager(this)
        mIngredientRecyclerView.layoutManager = mIngredientLayoutManager

        val mLayoutManager = LinearLayoutManager(this)
        mStepRecyclerView.layoutManager = mLayoutManager

        mIngreditentsAdapter = IngreditentsAdapter(this, data.ingredients as MutableList<Ingredient>)
        mIngredientRecyclerView.adapter = mIngreditentsAdapter

        mStepsAdapter = StepsAdapter(this, data.steps as MutableList<Step>)
        mStepRecyclerView.adapter = mStepsAdapter
    }

    private fun setUpToolbar() {
        if (supportActionBar != null) {
            supportActionBar?.title = receipeName
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
