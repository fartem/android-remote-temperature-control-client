package com.smlnskgmail.jaman.remotetemperaturecontrol.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.targets.BtPauseTarget

abstract class BaseFragment : Fragment(), BtPauseTarget {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View = inflater.inflate(
        getLayoutResId(),
        container,
        false
    )

    abstract fun getLayoutResId(): Int

    fun showBottomSheet(bottomSheet: BaseBottomSheet) {
        bottomSheet.show(
            activity!!.supportFragmentManager,
            bottomSheet::class.java.name
        )
    }

    override fun btOnPause() {}

}
