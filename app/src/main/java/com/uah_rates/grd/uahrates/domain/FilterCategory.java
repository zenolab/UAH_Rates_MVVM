package com.uah_rates.grd.uahrates.domain;

import com.uah_rates.grd.uahrates.domain.model.pojo.Rate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FilterCategory {

    private List<Rate> ratesListSorted = new ArrayList<>();


    public List<Rate> categoryFilter(CharSequence charSequence, @NotNull List<Rate> rates) {

        if (rates == null) {
            return null;
        }

        switch (charSequence.toString()) {
            case "CURRENCIES":
                return sortCurrencies(rates);
            case "METALS":
                return sortMetals(rates);
            case "XDR":
                return sortXDR(rates);
            case "ALL":
                return ratesListSorted = rates;
            default:
                // Log.e(ApiResponse.LOG_TAG, "no data");
                return null;
        }
    }


    private List<Rate> sortCurrencies(@Nullable List<Rate> rateList) {
        List<Rate> filteredList = new ArrayList<>();
        for (Rate row : rateList) {
            if (row.getR030() != 959 & row.getR030() != 960 & row.getR030() != 961 & row.getR030() != 962 & row.getR030() != 964) {
                filteredList.add(row);
            }
        }
        return ratesListSorted = filteredList;
    }

    private List<Rate> sortMetals(@Nullable List<Rate> rateList) {
        List<Rate> filteredList = new ArrayList<>();
        for (Rate row : rateList) {
            if (row.getR030() == 959 | row.getR030() == 961 | row.getR030() == 962 | row.getR030() == 964) {
                filteredList.add(row);
            }
        }
        return ratesListSorted = filteredList;
    }

    private List<Rate> sortXDR(@Nullable List<Rate> rateList) {
        List<Rate> filteredList = new ArrayList<>();
        for (Rate row : rateList) {
            if (row.getR030() == 960) {
                filteredList.add(row);
            }
        }
        return ratesListSorted = filteredList;
    }

}

