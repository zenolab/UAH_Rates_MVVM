package com.uah_rates.grd.uahrates.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;


import java.util.List;

import com.uah_rates.grd.uahrates.App;
import com.uah_rates.grd.uahrates.viewmodel.interactor.ApiResponse;
import com.uah_rates.grd.uahrates.model.pojo.Rate;
import com.uah_rates.grd.uahrates.api.RetrofitCallRateService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RateRepositoryImpl implements RateRepository {

    private static final String LOG_TAG = RateRepositoryImpl.class.getSimpleName();

    private RetrofitCallRateService apiService;


    public RateRepositoryImpl() {
        this.apiService = App.RetrofitClientInstance
                .getRetrofitInstance()
                .create(RetrofitCallRateService.class);
    }

    public LiveData<ApiResponse> getRates() {

        final MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();

        if(new ApiResponse().isCached()){
            liveData.setValue(App.CacheResponse.getCacheResponse());
            Log.e(LOG_TAG, "Caching uses");
            return liveData;
        }else {
            Call<List<Rate>> call = apiService.fetchRates();
            call.enqueue(new Callback<List<Rate>>() {
                @Override
                public void onResponse(Call<List<Rate>> call, Response<List<Rate>> response) {
                    liveData.setValue(new ApiResponse(response.body()));
                    App.CacheResponse.setCacheResponse( new ApiResponse( response.body() ));
                }
                @Override
                public void onFailure(Call<List<Rate>> call, Throwable t) {
                    liveData.setValue(new ApiResponse(t));
                }
            });
        }
        return liveData;
    }

}
