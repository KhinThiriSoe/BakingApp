package com.khinthirisoe.bakingapp.data.network

import com.khinthirisoe.bakingapp.data.model.BakingRecipe
import io.reactivex.Single
import retrofit2.http.GET

interface BakingApiService {

    @GET(ApiEndPoint.BAKING_RECIPES)
    fun getBakingRecipes(): Single<ArrayList<BakingRecipe>>
}
