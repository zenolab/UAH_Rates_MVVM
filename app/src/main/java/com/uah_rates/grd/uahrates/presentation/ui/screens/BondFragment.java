package com.uah_rates.grd.uahrates.presentation.ui.screens;

import android.app.ProgressDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.presentation.ui.adapter.BoundAdapter;
import com.uah_rates.grd.uahrates.domain.model.pojo.Bond;
import com.uah_rates.grd.uahrates.presentation.ui.viewmodel.BondsViewModel;

// bonds of domestic government loans - облигации государственных государственных займов
public class BondFragment extends Fragment {

    public static String LOG_TAG = "BondFragment_log";

    ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private BoundAdapter adapter;
    private BondsViewModel mViewModel;


    public static BondFragment newInstance() {
        return new BondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bond_fragment, container, false);

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
                (new Handler()).postDelayed(new Runnable() {

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
        mViewModel.getRates().observe(this, new Observer<List<?>>() {
            // Called when the data is changed.
            @Override
            public void onChanged(@Nullable List<?> bonds) {
                adapter = new BoundAdapter(getActivity(), (List<Bond>) bonds);
                recyclerView.setAdapter(adapter);
            }
        });
    }

}


