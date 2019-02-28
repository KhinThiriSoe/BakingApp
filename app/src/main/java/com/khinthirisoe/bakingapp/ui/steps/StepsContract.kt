package com.khinthirisoe.bakingapp.ui.steps

import com.khinthirisoe.bakingapp.ui.base.BasePresenter
import com.khinthirisoe.bakingapp.ui.base.BaseView
import com.khinthirisoe.bakingapp.ui.steps.player.MediaPlayer

interface StepsContract {

    interface Presenter : BasePresenter<StepsContract.View> {

        fun getPlayer(): MediaPlayer

        fun play(url: String)

        fun releasePlayer()

    }

    interface View: BaseView

}