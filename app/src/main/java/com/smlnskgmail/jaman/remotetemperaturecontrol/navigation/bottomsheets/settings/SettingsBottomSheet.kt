package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.settings

import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.BaseBottomSheet
import kotlinx.android.synthetic.main.bottom_sheet_settings.*

class SettingsBottomSheet : BaseBottomSheet() {

    private var disconnectListener: OnDisconnectListener? = null

    override fun initialize() {
        settings_disconnect.setOnClickListener {
            actionAndDismiss {
                disconnectListener?.closeConnection()
            }
        }
    }

    private fun actionAndDismiss(action: () -> Unit) {
        dismiss()
        action()
    }

    fun setBluetoothDisconnectListener(onDisconnectListener: OnDisconnectListener) {
        this.disconnectListener = onDisconnectListener
    }

    override fun getLayoutResId() = R.layout.bottom_sheet_settings

}