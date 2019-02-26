package com.uah_rates.grd.uahrates.domain.interactor.bond;

import android.util.Log;
import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.data.repository.DataCallbackListener;
import com.uah_rates.grd.uahrates.data.repository.bond.BondListener;
import com.uah_rates.grd.uahrates.data.repository.bond.BondRepository;
import com.uah_rates.grd.uahrates.domain.DomainConverter;
import com.uah_rates.grd.uahrates.domain.model.pojo.Bond;
import com.uah_rates.grd.uahrates.presentation.ui.viewmodel.PresentationListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BondUseCaseImpl implements BondUseCase {

    private static final String LOG_TAG = BondUseCaseImpl.class.getSimpleName();

    public BondUseCaseImpl() {

    }

    @Override
    public void getDomainListener(PresentationListener presentationListener) {
        getData(presentationListener);
    }

    private void getData(PresentationListener presentationListener) {
        new BondRepository(new BondListener() {
            @Override
            public void onSuccessAnswerOfBond(List<EntityBond> data) {
                presentationListener.successfulResponse(listAdapter(data));
            }

            @Override
            public void onErrorCodeAnswer(int code) {
                Log.e(LOG_TAG, "--- Error code " + code);
                presentationListener.errorResponse("Error code" + code);
            }

            @Override
            public void onError(String string) {
                Log.e(LOG_TAG, "--- Error " + string);
            }

            private List<Bond> listAdapter(List<EntityBond> data) {
                List<Bond> bondList = new ArrayList<Bond>(data.size());
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        for (EntityBond current : data) {
                            bondList.add(DomainConverter.bondRESTModelConverter(current));
                        }
                    }
                });
                t.start();
                return bondList;
            }

        });

    }

}
