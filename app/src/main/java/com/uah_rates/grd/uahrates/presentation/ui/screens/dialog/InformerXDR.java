package com.uah_rates.grd.uahrates.presentation.ui.screens.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.text.SpannableString;
import android.text.util.Linkify;

import com.uah_rates.grd.uahrates.R;

public class InformerXDR extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final SpannableString link = new SpannableString("www.imf.org");
        Linkify.addLinks(link, Linkify.ALL);

        builder.setTitle(getActivity().getString(R.string.title_xdr))
                .setMessage(getActivity().getString(R.string.about_xdr) +
                        " "+ link)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}
