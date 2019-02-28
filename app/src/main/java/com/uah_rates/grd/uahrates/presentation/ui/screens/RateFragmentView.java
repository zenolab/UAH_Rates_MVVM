package com.uah_rates.grd.uahrates.presentation.ui.screens;


import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.presentation.ui.adapter.DataAdapter;
import com.uah_rates.grd.uahrates.presentation.ui.viewmodel.UAHViewModel;
import java.util.List;

public class RateFragmentView extends Fragment {

    private final String LOG_TAG = RateFragmentView.class.getName();

    ProgressDialog progressDiaalog;
    private RecyclerView recyclerView;
    private UAHViewModel viewModel;
    protected DataAdapter dataAdapter;

    public RateFragmentView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_all_rates, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setHasFixedSize(true);
        recyclerView.hasFixedSize();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return false;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        viewModel = ViewModelProviders.of(this).get(UAHViewModel.class);
        viewModel.getRates().observe(this, new Observer<List<?>>() {
            // Called when the data is changed.
            @Override
            public void onChanged(@Nullable List<?> list) {
                dataAdapter = new DataAdapter(list);
                recyclerView.setAdapter(dataAdapter);
            }
        });
    }


}
