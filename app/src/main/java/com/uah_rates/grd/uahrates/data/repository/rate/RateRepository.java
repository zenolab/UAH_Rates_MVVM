package com.uah_rates.grd.uahrates.data.repository.rate;

import android.util.Log;
import com.uah_rates.grd.uahrates.App;
import com.uah_rates.grd.uahrates.data.entity.EntityRate;
import com.uah_rates.grd.uahrates.data.repository.DataCallbackListener;
import com.uah_rates.grd.uahrates.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class RateRepository  {

    private static final String LOG_TAG = RateRepository.class.getSimpleName();

    public RateRepository(RateListener listener) {
        getData(listener);
    }


    private void getData(RateListener asyncListener) {
        ApiService service = App.RetrofitClientInstance
                .getRetrofitInstance()
                .create(ApiService.class);

        Call<List<EntityRate>> call = service.fetchRates2();
        call.enqueue(new Callback<List<EntityRate>>() {

            @Override
            public void onResponse(Call<List<EntityRate>> call, Response<List<EntityRate>> response) {
                Log.e(LOG_TAG, "onResponse " + response.body());
                asyncListener.onSuccessAnswerOfRate(response.body());
            }

            @Override
            public void onFailure(Call<List<EntityRate>> call, Throwable t) {
                Log.e(LOG_TAG, "onFailure " + t.getMessage());
                asyncListener.onError("Repository error load data");
            }
        });
    }


}
