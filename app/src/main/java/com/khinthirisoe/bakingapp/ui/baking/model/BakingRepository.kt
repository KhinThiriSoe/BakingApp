package com.khinthirisoe.bakingapp.ui.baking.model

import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.network.ApiHelper
import io.reactivex.Single
import javax.inject.Inject

class BakingRepository @Inject

constructor(private val apiHelper: ApiHelper) {

    fun fetchRecipes(): Single<ArrayList<Recipe>> = apiHelper.getBakingRecipes()

}