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

        const val STEP_ID = "id"
        const val STEP_DESCRIPTION = "description"
        const val STEP_SHORT_DESCRIPTION = "short_description"
        const val STEP_VIDEO = "video"

        fun newInstance(step: Step): StepVideoFragment {

            val args = Bundle()
            args.putInt(STEP_ID, step.id)
            args.putString(STEP_DESCRIPTION, step.description)
            args.putString(STEP_SHORT_DESCRIPTION, step.shortDescription)
            args.putString(STEP_VIDEO, step.videoURL)

            val fragment = StepVideoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var presenter: StepsContract.Presenter? = null
    private lateinit var videoView: PlayerView
    private lateinit var descriptionView: TextView
    private lateinit var shortDescriptionView: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = StepsPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_step_video, container, false)
        videoView = rootView.findViewById(R.id.ep_video_view)
        descriptionView = rootView.findViewById(R.id.txt_description)
        shortDescriptionView = rootView.findViewById(R.id.txt_short_description)

        configureUI()

        return rootView
    }

    private fun configureUI() {

        val args = arguments
        val id = args?.getInt(STEP_ID)
        val description = args?.getString(STEP_DESCRIPTION)
        val shortDescription = args?.getString(STEP_SHORT_DESCRIPTION)
        val videolUrl = args?.getString(STEP_VIDEO)


        videoView.player = presenter!!.getPlayer().getPlayerImpl(context!!)
        presenter!!.play(videolUrl!!)
        descriptionView.text = description
        shortDescriptionView.text = shortDescription

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