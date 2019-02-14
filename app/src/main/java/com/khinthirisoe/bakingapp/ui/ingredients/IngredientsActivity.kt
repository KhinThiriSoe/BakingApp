package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.steps.view.StepsActivity






class IngredientsActivity : AppCompatActivity(), IngredientsFragment.OnFragmentInteractionListener {

    companion object {
        const val EXTRA_BAKING = "extra_baking"
    }

    private var ingredientsFragment: IngredientsFragment = IngredientsFragment.newInstance()

    private var bakingName: String? = null
    private var stepList: MutableList<Step>? = null
    private var bakingRecipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        if (intent.hasExtra(EXTRA_BAKING)) {
            bakingRecipe = intent.getParcelableExtra<Recipe>(EXTRA_BAKING)
            bakingName = bakingRecipe!!.name
            stepList = bakingRecipe!!.steps
        }

        supportFragmentManager.findFragmentById(R.id.list_selection_fragment) as IngredientsFragment

        setUpToolbar()
    }

    private fun setUpToolbar() {

        bakingName?.apply {
            if (supportActionBar != null) {
                supportActionBar?.title = bakingName
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }
        }
    }

    override fun onListItemClicked(step: Step) {
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
