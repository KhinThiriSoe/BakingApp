package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.data.prefs.AppPreferencesHelper
import com.khinthirisoe.bakingapp.ui.steps.view.StepsActivity
import com.khinthirisoe.bakingapp.ui.steps.view.StepsFragment


class IngredientsActivity : AppCompatActivity(), IngredientsFragment.OnFragmentInteractionListener {

    companion object {
        const val EXTRA_BAKING = "extra_baking"
    }

    private var fragmentContainer: FrameLayout? = null
    private var stepsFragment: StepsFragment? = null
    private var preferencesHelper: AppPreferencesHelper? = null

    private var ingredientsFragment: IngredientsFragment = IngredientsFragment.newInstance()

    private var bakingName: String? = null
    private var stepList: MutableList<Step>? = null
    private var bakingRecipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        preferencesHelper = AppPreferencesHelper(this)

        if (intent.hasExtra(EXTRA_BAKING)) {
            bakingRecipe = intent.getParcelableExtra<Recipe>(EXTRA_BAKING)
            bakingName = bakingRecipe!!.name
            stepList = bakingRecipe!!.steps
        }

        supportFragmentManager.findFragmentById(R.id.list_selection_fragment) as IngredientsFragment

        fragmentContainer = findViewById(R.id.fragment_container)
        preferencesHelper!!.isLargeScreen = fragmentContainer != null

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

        if (!preferencesHelper!!.isLargeScreen) {
            startActivity(
                Intent(this, StepsActivity::class.java)
                    .putExtra(StepsFragment.EXTRA_STEP, step)
                    .putParcelableArrayListExtra(StepsFragment.EXTRA_STEP_LIST, (ArrayList(stepList)))
            )

        } else {
            stepsFragment = StepsFragment.newInstance(stepList as ArrayList<Step>, step)
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    stepsFragment!!,
                    "Steps Fragment"
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
