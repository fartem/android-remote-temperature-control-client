package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.settings

import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.BaseBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.targets.BtDisconnectTarget
import kotlinx.android.synthetic.main.bottom_sheet_settings.*

class SettingsBottomSheet : BaseBottomSheet() {

    private var disconnectTarget: BtDisconnectTarget? = null

    override fun initialize() {
        settings_disconnect.setOnClickListener {
            actionAndDismiss {
                disconnectTarget?.btDisconnect()
            }
        }
    }

    private fun actionAndDismiss(action: () -> Unit) {
        dismiss()
        action()
    }

    fun setBtDisconnectListener(disconnectTarget: BtDisconnectTarget) {
        this.disconnectTarget = disconnectTarget
    }

    override fun getLayoutResId() = R.layout.bottom_sheet_settings

}
