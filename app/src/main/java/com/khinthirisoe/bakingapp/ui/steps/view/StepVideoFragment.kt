package com.khinthirisoe.bakingapp.ui.steps.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ui.PlayerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.steps.StepsContract
import com.khinthirisoe.bakingapp.ui.steps.presenter.StepsPresenter

class StepVideoFragment : Fragment(), StepsContract.View {

    companion object {
        const val EXTRA_STEP = "extra_step"

        fun newInstance(step: Step): StepVideoFragment {
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_STEP, step)
            val f = StepVideoFragment()
            f.arguments = bundle
            return f
        }
    }

    private var presenter: StepsContract.Presenter? = null
    private lateinit var videoView: PlayerView
    private lateinit var descriptionView: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = StepsPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_step_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        videoView = view.findViewById(R.id.ep_video_view)
        descriptionView = view.findViewById(R.id.txt_description)

        val step = arguments!!.getParcelable<Step>(EXTRA_STEP)
        videoView.player = presenter!!.getPlayer().getPlayerImpl(context!!)
        presenter!!.play(step.videoURL!!)
        descriptionView.text = step.description
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            presenter!!.releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            presenter!!.releasePlayer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDetachView()
    }
}