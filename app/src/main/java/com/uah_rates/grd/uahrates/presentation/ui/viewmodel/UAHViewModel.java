package com.uah_rates.grd.uahrates.presentation.ui.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.uah_rates.grd.uahrates.domain.interactor.RateUseCaseImpl;

import java.util.Collection;
import java.util.List;

public class UAHViewModel extends ViewModel {

    private static final String LOG_TAG = UAHViewModel.class.getSimpleName();

    //Holder for data
    private MutableLiveData<List<?>> listMutableLiveData;

    //LiveData holder
    public LiveData<List<?>> getRates() {

        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<List<?>>();
            loadRate();
        }
        return listMutableLiveData;
    }

    @Override
    protected void onCleared() {
        // clean up resources
    }

    private void loadRate() {
        RateUseCaseImpl rateUseCase = new RateUseCaseImpl();
        rateUseCase.setDomainListener(new PresentationListener() {


            @Override
            public void successfulResponse(Collection<?> collection) {
                listMutableLiveData.setValue((List<?>) collection);
            }

            @Override
            public void errorResponse(String error) {
                Log.d(LOG_TAG, "-- Error load data " + error);
            }
        });

    }

}
