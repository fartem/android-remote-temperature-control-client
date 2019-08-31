package com.smlnskgmail.jaman.remotetemperaturecontrol.components.dialogs

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object AlertDialog {

    fun show(context: Context, title: Int, message: Int, buttonText: Int,
             buttonClick: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context).setTitle(title)
            .setMessage(message)
            .setNegativeButton(buttonText, buttonClick)
            .setCancelable(false)
            .show()
    }

}