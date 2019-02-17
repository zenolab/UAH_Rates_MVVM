package com.uah_rates.grd.uahrates.model;

import androidx.lifecycle.LiveData;
import com.uah_rates.grd.uahrates.viewmodel.interactor.ApiResponse;


public interface RateRepository {

    LiveData<ApiResponse> getRates();
}
