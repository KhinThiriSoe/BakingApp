package com.khinthirisoe.bakingapp.ui.baking.model

import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.network.ApiHelper
import com.khinthirisoe.bakingapp.ui.baking.presenter.BakingPresenter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BakingRepository @Inject

constructor(private val apiHelper: ApiHelper) {

    fun fetchRecipes(listener: BakingPresenter.OnFetchRecipeListener) {

        apiHelper.getBakingRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<Recipe>> {
                override fun onSuccess(t: ArrayList<Recipe>) {
                    listener.onFetchRecipeSuccess(t)
                }

                override fun onSubscribe(d: Disposable) {
                    listener.onFetchRecipeDisposable(d)
                }

                override fun onError(e: Throwable) {
                    listener.onFetchRecipeFailed(e.message!!)
                }

            })
    }

}