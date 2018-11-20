package com.uah_rates.grd.uahrates.model;

import android.arch.lifecycle.LiveData;
import com.uah_rates.grd.uahrates.viewmodel.interactor.ApiResponse;


public interface RateRepository {
    LiveData<ApiResponse> getRates();
}
