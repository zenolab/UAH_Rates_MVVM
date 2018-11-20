package com.uah_rates.grd.uahrates.ui.screens;


import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.*;
import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.ui.screens.dialog.InformerXDR;



public class XDRFragmentView extends BaseRateFragment {

    private final String LOG_TAG = XDRFragmentView.class.getName();


    private final String TYPE_OF_RATE =  "XDR";
    private int template =  R.layout.custom_row_xdr;

    public XDRFragmentView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_currency, container, false);
        setHasOptionsMenu(true);
        initRatesListView(rootView);
        return rootView;
    }

    @Override
    protected void initRatesListView(View rootView) {

        setupView(rootView, template );
        initViewModel(TYPE_OF_RATE);
        setupSwipeRefresh(rootView,TYPE_OF_RATE);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_xdr, menu);

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info_xdr:

                AppCompatDialogFragment infoDialog = new InformerXDR();
                infoDialog.show(getActivity().getSupportFragmentManager(), "Informer");

                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
