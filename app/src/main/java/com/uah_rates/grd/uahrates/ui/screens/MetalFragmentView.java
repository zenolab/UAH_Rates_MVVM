package com.uah_rates.grd.uahrates.ui.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.uah_rates.grd.uahrates.R;




public class MetalFragmentView extends BaseRateFragment {

    private final String LOG_TAG = MetalFragmentView.class.getName();

    private final String TYPE_OF_RATE = "METALS";
    private int template =  R.layout.custom_row_metal;

    public MetalFragmentView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_metal, container, false);
        initRatesListView(rootView);
        return rootView;
    }

    @Override
    protected void initRatesListView(View rootView) {

        setupView(rootView, template );
        initViewModel(TYPE_OF_RATE);
        setupSwipeRefresh(rootView, TYPE_OF_RATE);

    }
}


/*

[
   {
      "r030":981,
      "txt":"Ларi",
      "rate":10.336666,
      "cc":"GEL",
      "exchangedate":"05.11.2018"
   },
   {
      "r030":959,
      "txt":"Золото",
      "rate":34758.103,
      "cc":"XAU",
      "exchangedate":"05.11.2018"
   },
   {
      "r030":961,
      "txt":"Срiбло",
      "rate":406.519,
      "cc":"XAG",
      "exchangedate":"05.11.2018"
   },
   {
      "r030":962,
      "txt":"Платина",
      "rate":23969.165,
      "cc":"XPT",
      "exchangedate":"05.11.2018"
   },
   {
      "r030":964,
      "txt":"Паладiй",
      "rate":30692.91,
      "cc":"XPD",
      "exchangedate":"05.11.2018"
   }
]


 */
