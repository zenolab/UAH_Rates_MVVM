package com.uah_rates.grd.uahrates.ui.screens;


import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.ui.adapter.DataAdapter;
import com.uah_rates.grd.uahrates.ui.adapter.listener.RecyclerTouchListener;
import com.uah_rates.grd.uahrates.ui.adapter.listener.RecyclerViewClickListener;
import com.uah_rates.grd.uahrates.model.pojo.Rate;
import com.uah_rates.grd.uahrates.viewmodel.RatesViewModel;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseRateFragment extends Fragment {

    private final String LOG_TAG = BaseRateFragment.class.getName();

    private ProgressDialog progressDialog;
    private RecyclerView   recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RatesViewModel viewModel;
    protected DataAdapter dataAdapter;

    protected List<Rate> searchedList = new ArrayList<>();


    protected abstract void initRatesListView(View view);


    protected void initViewModel(String TYPE_OF_RATE ) {

        viewModel = ViewModelProviders.of(this).get(RatesViewModel.class);
        setProgress(true);
        getData(viewModel,TYPE_OF_RATE);

    }


    //-------------------------------------Swipe------------------------------------------------------------------------
    protected void setupSwipeRefresh(View rootView,String category) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                ( new Handler()).postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        viewModel.invalidate();
                        getData(viewModel,category);
                        Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                        Log.d(LOG_TAG, " Updated by Swipe Refresh ");

                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                }, 2500);
            }
        });
    }
    //------------------------------------------------------------------------------------------------------------------

    protected void setupView(View rootView,int template) {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(getString(R.string.progress_title));
        progressDialog.setMessage(getString(R.string.progress_body));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setHasFixedSize(true);
        recyclerView.hasFixedSize();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataAdapter = new DataAdapter(getLayoutInflater(),template);

        initListClickListener( recyclerView );
        recyclerView.setAdapter(dataAdapter);
    }
    //------------------------------------------------------------------------------------------------------------------
    private void initListClickListener(RecyclerView recyclerView ) {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, final int position) {
              //  Toast.makeText(getActivity(), "Click "+position , Toast.LENGTH_LONG).show();
            }
            @Override
            public void onLongClick(View view, int position) {
              //  Toast.makeText(getActivity(), "Long press on position :"+position, Toast.LENGTH_LONG).show();
            }
        }));
    }
    //------------------------------------------------------------------------------------------------------------------
    protected void getData(RatesViewModel viewModel,String category) {
        viewModel.loadRates();

        viewModel.getApiResponse().observe(this, apiResponse -> {
            Log.e(LOG_TAG, "Observe called");
            if (apiResponse.getError() != null) {
                handleError(apiResponse.getError());
            } else {

                handleResponse(apiResponse.getListRates( category));
            }
        });

    }
    //------------------------------------------------------------------------------------------------------------------

    protected void handleResponse(List<Rate> rates) {
        setProgress(false);
        if (rates != null && rates.size() > 0) {

            searchedList = rates;
            dataAdapter.addRates(rates);

        } else {
            dataAdapter.clearRates();
            Log.v(LOG_TAG, "Cleared list");
        }
    }
    protected void handleError(Throwable error) {
        setProgress(false);
        dataAdapter.clearRates();
        Log.e(LOG_TAG, "error occured: " + error.toString());

        Toast.makeText(getActivity(), "Unable to load data!", Toast.LENGTH_SHORT).show();
    }

    protected void setProgress(boolean flag) {
        if (flag) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }


}
