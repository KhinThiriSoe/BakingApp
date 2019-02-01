package com.khinthirisoe.bakingapp.data.network

import com.khinthirisoe.bakingapp.data.model.ReceipeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiHelper {

    @GET(ApiEndPoint.GET_RECEIPE)
    fun getReceipeList(): Single<ArrayList<ReceipeResponse>>
}
