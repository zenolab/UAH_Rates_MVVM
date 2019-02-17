package com.uah_rates.grd.uahrates.domain.interactor;

import android.util.Log;
import com.uah_rates.grd.uahrates.domain.interactor.model.pojo.Rate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.ArrayList;
import java.util.List;


/**
 * http code handler
 */
public class ApiResponse {

    protected static final String LOG_TAG = ApiResponse.class.getSimpleName();


    private Throwable error;
    private static List<Rate> rates = null;
    //private List<Rate> ratesListSorted = new ArrayList<>();


    public List<Rate> getter() {
        return this.rates;
    }

    public List<Rate> setter(List<Rate> rate) {
        Log.v(LOG_TAG, "Setting cache");
        return this.rates = rate;
    }


    public ApiResponse() {
        // this.error = new Throwable();
    }

    public ApiResponse(List<Rate> rates) {
        this.rates = rates;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.rates = null;
    }


    public List<Rate> getListRates(String str) {
        return new FilterCategory().categoryFilter(str, rates);
    }

    public Throwable getError() {
        return error;
    }

    public boolean isCached() {
        if (rates != null) {
            return true;
        } else {
            return false;
        }
    }

}


