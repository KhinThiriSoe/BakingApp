package com.khinthirisoe.bakingapp.data.network

import com.khinthirisoe.bakingapp.data.model.ReceipeResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiHelper {

    @GET(ApiEndPoint.GET_RECEIPE)
    fun getReceipeList(): Observable<ReceipeResponse>
}
