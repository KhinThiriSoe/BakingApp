package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.data.prefs.AppPreferencesHelper
import com.khinthirisoe.bakingapp.ui.steps.StepsActivity
import com.khinthirisoe.bakingapp.ui.steps.StepsFragment

class IngredientsActivity : AppCompatActivity(), IngredientsFragment.OnFragmentInteractionListener {

    companion object {
        const val EXTRA_BAKING = "extra_baking"

        fun createIntent(context: Context, recipe: Recipe): Intent {
            return Intent(context, IngredientsActivity::class.java).putExtra(EXTRA_BAKING, recipe)
        }
    }

    private var fragmentContainer: FrameLayout? = null
    private var stepsFragment: StepsFragment? = null
    private var preferencesHelper: AppPreferencesHelper? = null

    private var bakingName: String? = null
    private var stepList: ArrayList<Step>? = null
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

    override fun onListItemClicked(position: Int) {

        if (!preferencesHelper!!.isLargeScreen) {
            startActivity(
                Intent(this, StepsActivity::class.java)
                    .putExtra(StepsActivity.EXTRA_STEP_POSITION, position)
                    .putParcelableArrayListExtra(StepsActivity.EXTRA_STEP_LIST, stepList)
            )

        } else {
            stepsFragment = StepsFragment.newInstance(stepList!!, position)
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