package com.uah_rates.grd.uahrates.presentation.ui.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.Collection;
import java.util.List;

import com.uah_rates.grd.uahrates.domain.model.pojo.Bond;
import com.uah_rates.grd.uahrates.domain.interactor.bond.BondUseCaseImpl;

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
            loadBond();
        }
        return bondsList;
    }

    @Override
    protected void onCleared() {
        // clean up resources
    }

    private void loadBond() {
        BondUseCaseImpl bondUseCase = new BondUseCaseImpl();
        bondUseCase.getDomainListener(new PresentationListener() {

            @Override
            public void successfulResponse(List<?> list) {
                bondsList.setValue((List<Bond>) list);
            }

            @Override
            public void errorResponse(String error) {
                Log.d(LOG_TAG, "-- Error load data " + error);
            }
        });

    }

}