package com.uah_rates.grd.uahrates.ui.screens.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.text.SpannableString
import android.view.View
import com.uah_rates.grd.uahrates.R


class InfoDialogFragment : AppCompatDialogFragment() {

    val link = SpannableString("https://www.bank.gov.ua")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!)
                .setTitle(activity!!.getString(R.string.informer_app))
                .setMessage(activity!!.getString(R.string.informer_menu) +
                    " "+ link)
                .setNegativeButton(android.R.string.cancel, null)
                .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState) }
}
