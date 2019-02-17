package com.uah_rates.grd.uahrates.presentation.ui.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.uah_rates.grd.uahrates.R;

public class CurrencyFragmentView extends BaseRateFragment {

    private final String LOG_TAG = CurrencyFragmentView.class.getName();

    private final String TYPE_OF_RATE = "CURRENCIES";
    private int template =  R.layout.custom_row;


    public CurrencyFragmentView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_currency, container, false);
        initRatesListView(rootView);
        return rootView;
    }

    @Override
    protected void initRatesListView(View rootView) {

        setupView(rootView, template );
        initViewModel(TYPE_OF_RATE);
        setupSwipeRefresh(rootView,TYPE_OF_RATE);

    }

}
