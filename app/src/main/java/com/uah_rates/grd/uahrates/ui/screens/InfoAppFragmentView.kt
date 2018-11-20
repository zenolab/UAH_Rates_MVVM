package com.uah_rates.grd.uahrates.ui.screens


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uah_rates.grd.uahrates.R

class InfoAppFragmentView : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info_app, container, false)
    }


}
