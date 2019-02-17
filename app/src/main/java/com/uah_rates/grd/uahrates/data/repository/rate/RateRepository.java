package com.uah_rates.grd.uahrates.data.repository.rate;

import androidx.lifecycle.LiveData;
import com.uah_rates.grd.uahrates.domain.interactor.ApiResponse;


public interface RateRepository {

    LiveData<ApiResponse> getRates();
}
