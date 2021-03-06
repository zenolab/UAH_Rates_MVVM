package com.uah_rates.grd.uahrates.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;



import java.util.List;

import com.uah_rates.grd.uahrates.App;
import com.uah_rates.grd.uahrates.model.pojo.Bond;
import com.uah_rates.grd.uahrates.api.RetrofitCallRateService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//bonds of domestic government loans - облигации государственных государственных займов
public class BondsViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private static final String LOG_TAG = BondsViewModel.class.getSimpleName();
    //Holder for data
    private MutableLiveData<List<Bond>> bondsList;
    //LiveData holder
    public LiveData<List<Bond>> getRates() {

        if (bondsList == null) {
            bondsList = new MutableLiveData<>();
            loadRates();
        }
        return bondsList;
    }

    @Override
    protected void onCleared() {
        // clean up resources
    }

    /**
     * Repository
     */
    private void loadRates() {

        RetrofitCallRateService service = App.RetrofitClientInstance
                .getRetrofitInstance()
                .create(RetrofitCallRateService.class);


        Call<List<Bond>> call = service.fetchBonds();
        call.enqueue(new Callback<List<Bond>>() {

            @Override
            public void onResponse(Call<List<Bond>> call, Response<List<Bond>> response) {

                Log.e(LOG_TAG, "******* > RETROFIT - onResponse ");
                bondsList.setValue(response.body());
                Log.d(LOG_TAG, "******* > RETROFIT - JSON  all " + response);
                Log.w(LOG_TAG, "******* > RETROFIT - JSON code " + response.code());
                Log.e(LOG_TAG, "******* > RETROFIT - JSON " + response.body());
            }

            @Override
            public void onFailure(Call<List<Bond>> call, Throwable t) {
                Log.e(LOG_TAG, "******* > RETROFIT - onFailure "+t.getMessage());

            }
        });
    }
}