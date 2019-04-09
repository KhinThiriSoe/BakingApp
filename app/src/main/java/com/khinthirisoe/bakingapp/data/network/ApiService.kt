package com.khinthirisoe.bakingapp.data.network

import com.khinthirisoe.bakingapp.data.model.Recipe
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(ApiUrl.BAKING_RECIPES)
    fun getBakingRecipes(): Single<ArrayList<Recipe>>

}
