package com.khinthirisoe.bakingapp.ui.receipes.model

import com.khinthirisoe.bakingapp.data.model.ReceipeResponse
import com.khinthirisoe.bakingapp.data.network.ApiHelper
import com.khinthirisoe.bakingapp.ui.receipes.presenter.ReceipesPresenter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReceipesInteractor @Inject
constructor(private val mApiHelper: ApiHelper) {

    fun fetchReceipes(listener: ReceipesPresenter.OnFetchReceipeListener) {

        mApiHelper.getReceipeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ReceipeResponse> {
                override fun onSuccess(t: ReceipeResponse) {
                    listener.onFetchReceipeSuccess(t)
                }

                override fun onSubscribe(d: Disposable) {
                    listener.onFetchReceipeDisposable(d)
                }

                override fun onError(e: Throwable) {
                    listener.onFetchReceipeFailed(e.message!!)
                }

            })

    }

}