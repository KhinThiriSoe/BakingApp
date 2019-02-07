package com.khinthirisoe.bakingapp.ui.steps.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.khinthirisoe.bakingapp.data.model.Step
import com.nakama.arraypageradapter.ArrayFragmentStatePagerAdapter

class StatePageAdapter(fragmentManager: FragmentManager, steps: ArrayList<Step>) :
    ArrayFragmentStatePagerAdapter<Step>(fragmentManager, steps) {

    override fun getFragment(step: Step, position: Int): Fragment {
        return StepVideoFragment.newInstance(step)
    }
}
