package com.uah_rates.grd.uahrates.ui.screens;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.ui.adapter.BoundAdapter;
import com.uah_rates.grd.uahrates.model.pojo.Bond;
import com.uah_rates.grd.uahrates.viewmodel.BondsViewModel;

// bonds of domestic government loans - облигации государственных государственных займов
public class BondFragment extends Fragment {

    public static String LOG_TAG = "BondFragment_log";

    ProgressDialog progressDoalog;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private BoundAdapter adapter;
    private BondsViewModel mViewModel;

    //
    public static BondFragment newInstance() {
        return new BondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =   inflater.inflate(R.layout.bond_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewBond);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setupSwipeRefresh(view);
        return view;
    }

    private void setupSwipeRefresh(View rootView) {

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                ( new Handler()).postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        Log.d(LOG_TAG, " SwipeRefresh ");
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                }, 3500);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        mViewModel = ViewModelProviders.of(this).get(BondsViewModel.class);
        mViewModel.getRates().observe(this, new Observer<List<Bond>>() {
             // Called when the data is changed.
            @Override
            public void onChanged(@Nullable List<Bond> bonds) {
                adapter = new BoundAdapter(getActivity(), bonds);
                recyclerView.setAdapter(adapter);
            }
        });
    }

}


