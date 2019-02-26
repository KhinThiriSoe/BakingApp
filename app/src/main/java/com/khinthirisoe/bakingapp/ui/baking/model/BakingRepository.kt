package com.khinthirisoe.bakingapp.ui.baking.model

import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.network.ApiService
import io.reactivex.Single
import javax.inject.Inject

class BakingRepository @Inject

constructor(private val apiService: ApiService) {

    fun fetchRecipes(): Single<ArrayList<Recipe>> = apiService.getBakingRecipes()

}