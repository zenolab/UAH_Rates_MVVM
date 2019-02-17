package com.uah_rates.grd.uahrates.presentation.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;
import android.util.Log;
import com.uah_rates.grd.uahrates.domain.interactor.ApiResponse;
import com.uah_rates.grd.uahrates.data.repository.rate.RateRepository;
import com.uah_rates.grd.uahrates.data.repository.rate.RateRepositoryImpl;


public class RatesViewModel extends ViewModel {

    private static final String LOG_TAG = RatesViewModel.class.getSimpleName();

    private LiveData<ApiResponse> ratesSource ;
    private MediatorLiveData<ApiResponse> apiResponseMediatorLiveData;
    private RateRepository ratesRepository;

    public RatesViewModel() {
        this.apiResponseMediatorLiveData = new MediatorLiveData<>();
        this.ratesRepository = new RateRepositoryImpl();
        Log.wtf("RatesViewModel", "we are here ");
    }

    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return apiResponseMediatorLiveData;
    }

    public void loadRates() {
        ratesSource = ratesRepository.getRates();
        apiResponseMediatorLiveData.addSource(
                // Returns true if this LiveData has active observers.
                ratesSource, apiResponse -> {
                    if (this.apiResponseMediatorLiveData.hasActiveObservers()) {
                        this.apiResponseMediatorLiveData.removeSource(ratesSource);
                    }
                    this.apiResponseMediatorLiveData.setValue(apiResponse);
                }
        );
    }

    public void invalidate(){
         new ApiResponse().setter(null);
    }

    @Override
    public void onCleared() {
        Log.v(LOG_TAG, "onCleared() of RatesViewModel" );
    }

}