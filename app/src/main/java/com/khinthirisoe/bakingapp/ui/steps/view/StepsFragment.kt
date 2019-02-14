package com.khinthirisoe.bakingapp.ui.steps.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step

class StepsFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: StepPagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_steps, container, false)

        setUpView(view)

        configureUI()

        return view
    }

    private fun setUpView(view: View) {

        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
    }

    private fun configureUI() {

        val bundle = activity!!.intent.extras
        if (bundle != null) {
            val stepList = bundle.getParcelableArrayList<Step>(EXTRA_STEP_LIST)
            val step = bundle.getParcelable<Step>(EXTRA_STEP)

            pagerAdapter = StepPagerAdapter(activity!!.supportFragmentManager, stepList)

            viewPager.adapter = pagerAdapter
            viewPager.currentItem = step.id
            tabLayout.setupWithViewPager(viewPager)

        }
    }

    companion object {

        const val EXTRA_STEP = "extra_step"
        const val EXTRA_STEP_LIST = "extra_step_list"

        @JvmStatic
        fun newInstance(): StepsFragment {
            return StepsFragment()
        }
    }
}
