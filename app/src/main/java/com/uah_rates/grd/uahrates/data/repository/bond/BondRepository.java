package com.uah_rates.grd.uahrates.data.repository.bond;

import android.util.Log;
import com.uah_rates.grd.uahrates.App;
import com.uah_rates.grd.uahrates.network.ApiService;
import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

import static com.uah_rates.grd.uahrates.presentation.ui.screens.BondFragment.LOG_TAG;

public class BondRepository {

    BondListener listener;

    public BondRepository(BondListener listener) {
        this.listener = listener;
    }

    public void getData() {

        ApiService service = App.RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);

        Call<List<EntityBond>> call = service.fetchBonds();
        call.enqueue(new Callback<List<EntityBond>>() {

            @Override
            public void onResponse(Call<List<EntityBond>> call, Response<List<EntityBond>> response) {
                Log.e(LOG_TAG, "onResponse " + response.body());
                listener.onSuccessAnswerOfBond(response.body());
            }

            @Override
            public void onFailure(Call<List<EntityBond>> call, Throwable t) {
                Log.e(LOG_TAG, "RETROFIT - onFailure " + t.getMessage());
                listener.onError("Repository error load data");
            }
        });

    }

}