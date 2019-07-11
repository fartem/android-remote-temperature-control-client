package com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet : BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
    }

    abstract fun initialize()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = LayoutInflater.from(container!!.context!!)
        .inflate(getLayoutResId(), container, false)

    abstract fun getLayoutResId(): Int

}