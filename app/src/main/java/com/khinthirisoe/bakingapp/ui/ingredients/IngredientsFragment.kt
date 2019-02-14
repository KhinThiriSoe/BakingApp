package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.data.db.repository.IngredientsRepository
import com.khinthirisoe.bakingapp.data.model.Ingredient
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.data.prefs.AppPreferencesHelper
import com.khinthirisoe.bakingapp.ui.widget.BakingAppWidget


class IngredientsFragment : Fragment(), StepsAdapter.StepRecyclerViewClickListener {

    private lateinit var ingredientRecyclerView: RecyclerView
    private var ingredientsAdapter: IngredientsAdapter? = null
    private lateinit var stepRecyclerView: RecyclerView
    private var stepsAdapter: StepsAdapter? = null
    private lateinit var repository: IngredientsRepository
    private lateinit var pref: AppPreferencesHelper

    private var stepList: MutableList<Step>? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(com.khinthirisoe.bakingapp.R.layout.fragment_ingredients, container, false)

        repository = IngredientsRepository(context!!)
        pref = AppPreferencesHelper(context!!)

        setUpView(view)

        val bundle = activity!!.intent.extras
        if (bundle != null) {
            val recipe = bundle.getParcelable<Recipe>(IngredientsActivity.EXTRA_BAKING)

            configureUI(recipe)
        }

        return view
    }

    private fun setUpView(view: View) {

        ingredientRecyclerView = view.findViewById(com.khinthirisoe.bakingapp.R.id.ingredient_recyclerView)
        stepRecyclerView = view.findViewById(com.khinthirisoe.bakingapp.R.id.step_recyclerView)

        val mIngredientLayoutManager = LinearLayoutManager(context)
        ingredientRecyclerView.layoutManager = mIngredientLayoutManager

        val mLayoutManager = LinearLayoutManager(context)
        stepRecyclerView.addItemDecoration(
            DividerItemDecoration(
                stepRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        stepRecyclerView.layoutManager = mLayoutManager
    }

    private fun configureUI(recipe: Recipe?) {

        recipe?.apply {

            stepList = recipe.steps

            repository.removeIngredient()

            repository.saveIngredients(recipe.ingredients)

            pref.recipeName = recipe.name

            activity!!.runOnUiThread {
                BakingAppWidget.sendRefreshBroadcast(context!!)
            }

            ingredientsAdapter =
                IngredientsAdapter(recipe.ingredients as MutableList<Ingredient>)
            ingredientRecyclerView.adapter = ingredientsAdapter

            stepsAdapter = StepsAdapter(recipe.steps as MutableList<Step>, this@IngredientsFragment)
            stepRecyclerView.adapter = stepsAdapter
        }
    }

    override fun listItemClick(step: Step) {
        listener?.onListItemClicked(step)
    }

    interface OnFragmentInteractionListener {
        fun onListItemClicked(step: Step)
    }

    companion object {
        @JvmStatic
        fun newInstance(): IngredientsFragment {
            return IngredientsFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}
