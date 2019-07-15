package com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.settings

import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.BaseBottomSheet

class SettingsBottomSheet : BaseBottomSheet() {

    private var onDisconnectListener: OnDisconnectListener? = null

    override fun initialize() {

    }

    fun setBluetoothDisconnectListener(onDisconnectListener: OnDisconnectListener) {
        this.onDisconnectListener = onDisconnectListener
    }

    override fun getLayoutResId() = R.layout.bottom_sheet_settings

}