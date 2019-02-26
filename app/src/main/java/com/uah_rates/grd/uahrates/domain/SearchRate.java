package com.uah_rates.grd.uahrates.domain;

import android.util.Log;
import com.uah_rates.grd.uahrates.domain.model.pojo.Rate;

import java.util.ArrayList;
import java.util.List;

public class SearchRate {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private List<Rate> rateList;
    private List<Rate> ratesListSorted;

    public SearchRate(List<Rate> rateList) {

        this.rateList = rateList;
        this.ratesListSorted = new ArrayList<>();
    }

    public List<Rate> searchFilterOfRate(CharSequence charSequence) {
        String charString = charSequence.toString();
        if (charString.isEmpty()) {
            return ratesListSorted = rateList;
        } else {
            List<Rate> filteredList = new ArrayList<>();
            for (Rate row : rateList) {
                Log.e(LOG_TAG, "row " + row.getCc().toLowerCase().contains(charString.toLowerCase()));
                if (row.getCc().toLowerCase().contains(charString.toLowerCase()) | row.getTxt().toLowerCase().contains(charString.toLowerCase())) {
                    filteredList.add(row);
                    Log.e(LOG_TAG, "filteredList " + filteredList.size());
                }
            }

            return ratesListSorted = filteredList;

        }

    }
}
