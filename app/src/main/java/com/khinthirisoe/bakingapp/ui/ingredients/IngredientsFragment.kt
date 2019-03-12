package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.db.repository.IngredientsRepository
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.prefs.AppPreferencesHelper
import com.khinthirisoe.bakingapp.ui.widget.BakingAppWidget
import kotlinx.android.synthetic.main.fragment_ingredients.*


class IngredientsFragment : Fragment(), StepsAdapter.StepsRecyclerViewClickListener{

    private var ingredientsAdapter: IngredientsAdapter? = null
    private var stepsAdapter: StepsAdapter? = null
    private lateinit var repository: IngredientsRepository
    private lateinit var preferencesHelper: AppPreferencesHelper

    private var recipe: Recipe? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_ingredients, container, false)

        repository = IngredientsRepository(context!!)
        preferencesHelper = AppPreferencesHelper(context!!)

        val bundle = activity!!.intent.extras
        if (bundle != null) {
            recipe = bundle.getParcelable<Recipe>(IngredientsActivity.EXTRA_BAKING)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {

        setupIngredients()

        setUpSteps()

        setUpUI()

    }

    private fun setupIngredients() {
        val ingredientLayoutManager = LinearLayoutManager(context)
        ingredient_recyclerView.layoutManager = ingredientLayoutManager

        ingredientsAdapter = IngredientsAdapter(null)

        ingredientsAdapter?.setIngredients(recipe!!.ingredients)
        ingredient_recyclerView.adapter = ingredientsAdapter

    }

    private fun setUpSteps() {

        val stepLayoutManager = LinearLayoutManager(context)
        step_recyclerView.addItemDecoration(
            DividerItemDecoration(
                step_recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        step_recyclerView.layoutManager = stepLayoutManager

        stepsAdapter = StepsAdapter(null, this)

        stepsAdapter?.setSteps(recipe!!.steps)
        step_recyclerView.adapter = stepsAdapter
    }

    private fun setUpUI() {
        recipe?.let {

            repository.removeIngredient()

            repository.saveIngredients(recipe!!.ingredients)

            preferencesHelper.recipeName = recipe!!.name

            activity!!.runOnUiThread {
                BakingAppWidget.sendRefreshBroadcast(context!!)
            }

            ingredientsAdapter =
                IngredientsAdapter(recipe!!.ingredients)
            ingredient_recyclerView.adapter = ingredientsAdapter

            stepsAdapter = StepsAdapter(recipe!!.steps, this)
            step_recyclerView.adapter = stepsAdapter
        }
    }

    override fun listItemClick(position: Int) {
        listener?.onListItemClicked(position)
    }

    interface OnFragmentInteractionListener {
        fun onListItemClicked(position: Int)
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
