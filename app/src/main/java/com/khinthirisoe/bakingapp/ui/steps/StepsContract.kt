package com.khinthirisoe.bakingapp.ui.steps

import com.khinthirisoe.bakingapp.ui.steps.player.MediaPlayer

interface StepsContract {

    interface View

    interface Presenter : StepsContract.View {

        fun getPlayer(): MediaPlayer

        fun play(url: String)

        fun releasePlayer()

        fun onDetachView()

    }
}