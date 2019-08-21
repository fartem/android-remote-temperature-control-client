package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.BaseBottomSheet

abstract class BaseFragment : Fragment(), FragmentPauseTarget {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeFragment(view)
    }

    abstract fun initializeFragment(view: View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?)
            = inflater.inflate(getLayoutResId(), container, false)

    abstract fun getLayoutResId(): Int

    fun showBottomSheet(bottomSheet: BaseBottomSheet) {
        bottomSheet.show(activity!!.supportFragmentManager, bottomSheet::class.java.name)
    }

    override fun fragmentPause() {}

}