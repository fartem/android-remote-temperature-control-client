package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object AppDialog {

    fun show(context: Context, titleResId: Int, message: Int, buttonText: Int,
             buttonClick: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context).setTitle(titleResId)
            .setMessage(message)
            .setNegativeButton(buttonText, buttonClick)
            .setCancelable(false)
            .show()
    }

}