package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.settings

import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.BaseBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.targets.BtDisconnectTarget
import kotlinx.android.synthetic.main.bottom_sheet_settings.*

class SettingsBottomSheet : BaseBottomSheet() {

    override fun initialize() {
        settings_disconnect.setOnClickListener {
            actionAndDismiss {
                (activity as BtDisconnectTarget).btDisconnect()
            }
        }
    }

    private fun actionAndDismiss(action: () -> Unit) {
        dismiss()
        action()
    }

    override fun getLayoutResId() = R.layout.bottom_sheet_settings

}
