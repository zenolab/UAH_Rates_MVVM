package com.uah_rates.grd.uahrates.viewmodel.interactor;

import android.util.Log;
import com.uah_rates.grd.uahrates.model.pojo.Rate;
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
    private  static List<Rate> rates = null;
    private List<Rate> ratesListSorted = new ArrayList<>();


    public List<Rate> getter() {
        return this.rates;
    }

    public List<Rate> setter(List<Rate> rate) {
        Log.v(LOG_TAG, "Setting cache");
        return this.rates = rate;
    }


    public ApiResponse() {

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
        return new FilterCategory().categoryFilter(str,rates);
    }

    public Throwable getError() {
        return error;
    }

    public   boolean isCached(){
        if(rates != null){
            return true;
        }else{
            return false;
        }
    }

}
//-----------------------------------------Filter-----------------------------------------------------------------------
 class FilterCategory{

    private List<Rate> ratesListSorted = new ArrayList<>();


    public List <Rate> categoryFilter(CharSequence charSequence, @NotNull List<Rate> rates) {

        if(rates == null){
            return null;
        }

        switch(charSequence.toString())
        {
            case "CURRENCIES" :
                return sortCurrencies(rates);
            case "METALS" :
                return   sortMetals(rates);
            case "XDR" :
                return   sortXDR(rates);
            case "ALL" :
                return ratesListSorted = rates;
            default :
                Log.e(ApiResponse.LOG_TAG, "no data" );
                return null;
        }
    }



    private List<Rate> sortCurrencies(@Nullable List<Rate> rateList ) {
        List<Rate> filteredList = new ArrayList<>();
        for (Rate row : rateList) {
            if (row.getR030()!=959 & row.getR030() != 960 & row.getR030() != 961 & row.getR030() != 962 & row.getR030() != 964 ) {
                filteredList.add(row);
            }
        }
        return ratesListSorted = filteredList;
    }

    private List<Rate> sortMetals(@Nullable List<Rate> rateList ) {
        List<Rate> filteredList = new ArrayList<>();
        for (Rate row : rateList) {
            if (row.getR030()==959  | row.getR030() == 961 | row.getR030() == 962 | row.getR030() == 964 ) {
                filteredList.add(row);
            }
        }
        return  ratesListSorted = filteredList;
    }

    private List<Rate> sortXDR(@Nullable List<Rate> rateList ) {
        List<Rate> filteredList = new ArrayList<>();
        for (Rate row : rateList) {
            if (row.getR030()==960) {
                filteredList.add(row);
            }
        }
        return ratesListSorted = filteredList;
    }

}



