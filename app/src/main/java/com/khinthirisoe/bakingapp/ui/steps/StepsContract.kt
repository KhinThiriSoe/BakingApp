package com.khinthirisoe.bakingapp.ui.steps

import com.khinthirisoe.bakingapp.ui.steps.player.MediaPlayer

interface StepsContract {

    interface Presenter {

        fun deactivate()

        fun getPlayer(): MediaPlayer

        fun play(url: String)

        fun releasePlayer()

    }

    interface View

}