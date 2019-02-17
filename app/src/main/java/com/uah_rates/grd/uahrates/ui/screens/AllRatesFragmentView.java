package com.uah_rates.grd.uahrates.ui.screens;



import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.app.SearchManager;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.viewmodel.interactor.SearchRate;


public class AllRatesFragmentView extends BaseRateFragment {

    private final String LOG_TAG = AllRatesFragmentView.class.getName();

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private final String TYPE_OF_RATE = "ALL";
    private int template =  R.layout.custom_row;

    public AllRatesFragmentView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_all_rates, container, false);
        initRatesListView(rootView);
        return rootView;

    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void initRatesListView(View rootView) {

        setupView(rootView,template);
        initViewModel(TYPE_OF_RATE);
        setupSwipeRefresh(rootView,TYPE_OF_RATE);

    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    dataAdapter.addRates( new SearchRate(searchedList).searchFilterOfRate(newText));
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    dataAdapter.addRates( new SearchRate(searchedList).searchFilterOfRate(query));
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
            searchView.clearFocus();
        }
        super.onCreateOptionsMenu(menu, inflater);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

}
