package com.smlnskgmail.jaman.remotetemperaturecontrol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.MonitorFragment
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.targets.BtPauseTarget

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(MonitorFragment())
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment, CURRENT_FRAGMENT_TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onPause() {
        super.onPause()
        handleOnPause()
    }

    private fun handleOnPause() {
        val currentFragment = supportFragmentManager.findFragmentByTag(CURRENT_FRAGMENT_TAG)
        if (currentFragment != null && currentFragment is BtPauseTarget) {
            currentFragment.btOnPause()
        }
    }

    companion object {

        private const val CURRENT_FRAGMENT_TAG = "current_fragment"

    }

}
