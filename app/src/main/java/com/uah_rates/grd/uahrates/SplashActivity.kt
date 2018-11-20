package com.uah_rates.grd.uahrates

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager

import com.uah_rates.grd.uahrates.ui.MainBaseActivity

class SplashActivity : AppCompatActivity() {


    private var sharedPreferences: SharedPreferences? = null
    private var mDelayHandler: Handler? = null
    private var SPLASH_DELAY: Long = 2000


   internal val mRunnable: Runnable = Runnable {

        if (!isFinishing) {

            val intent = Intent(applicationContext, MainBaseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_splash)

        loadPref();
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    private fun loadPref() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val prefValue = sharedPreferences!!.getString("RATE_PREF_KEY", "")

        if (prefValue == "Off") {
            SPLASH_DELAY = 0
        } else {
            setContentView(R.layout.activity_splash)
        }

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}
