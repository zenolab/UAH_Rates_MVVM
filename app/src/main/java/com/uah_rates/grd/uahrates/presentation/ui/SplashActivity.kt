package com.uah_rates.grd.uahrates.presentation.ui

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.preference.PreferenceManager
import android.view.animation.BounceInterpolator
import com.uah_rates.grd.uahrates.Invariance
import com.uah_rates.grd.uahrates.R

import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    private var sharedPreferences: SharedPreferences? = null
    private var mDelayHandler: Handler? = null
    private var SPLASH_DELAY: Long = 2000


    private val ANIMATION_DURATION:Long = 1900;


   internal val mRunnable: Runnable = Runnable {

        if (!isFinishing) {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadPref();

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    private fun loadPref() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val prefValue = sharedPreferences!!.getString(Invariance.SP_STORAGE_KEY, "")

        if (prefValue == "Off") {
            SPLASH_DELAY = 0
        } else {
            setContentView(R.layout.activity_splash)
            startAnimation();
        }
    }

    private fun startAnimation(){

        // Intro animation configuration.
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            textTitleOfSplash.scaleX = value
            textTitleOfSplash.scaleY = value
        }
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = ANIMATION_DURATION

        // Set animator listener.
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                mDelayHandler = Handler()
                mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
            }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })
        valueAnimator.start()
    }


    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}
