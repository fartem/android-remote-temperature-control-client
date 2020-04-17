package com.smlnskgmail.jaman.remotetemperaturecontrol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.MonitorFragment
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.targets.BtPauseTarget

class MainActivity : AppCompatActivity() {

    companion object {

        private const val currentFragmentTag = "current_fragment"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMonitorFragment()
    }

    private fun showMonitorFragment() {
        supportFragmentManager.beginTransaction()
            .add(
                android.R.id.content,
                MonitorFragment(),
                currentFragmentTag
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onPause() {
        super.onPause()
        handleOnPause()
    }

    private fun handleOnPause() {
        val currentFragment = supportFragmentManager.findFragmentByTag(
            currentFragmentTag
        )
        if (currentFragment != null && currentFragment is BtPauseTarget) {
            currentFragment.btOnPause()
        }
    }

}
