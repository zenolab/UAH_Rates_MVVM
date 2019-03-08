package com.uah_rates.grd.uahrates.domain.interactor;

import android.util.Log;
import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.data.repository.bond.BondListener;
import com.uah_rates.grd.uahrates.data.repository.bond.BondRepository;
import com.uah_rates.grd.uahrates.domain.DomainConverter;
import com.uah_rates.grd.uahrates.domain.model.pojo.Bond;
import com.uah_rates.grd.uahrates.presentation.ui.viewmodel.PresentationListener;

import java.util.ArrayList;
import java.util.List;

public class BondUseCaseImpl<P extends PresentationListener> implements UseCase {

    private static final String LOG_TAG = BondUseCaseImpl.class.getSimpleName();

    private P listener;

    public BondUseCaseImpl() {

    }

    @Override
    public <L> void setDomainListener(L listener) {
        this.listener = (P) listener;
        getRepository( );
    }

    private void getRepository() {
        new BondRepository(new BondListener() {
            @Override
            public void onSuccessAnswerOfBond(List<EntityBond> data) {
                listener.successfulResponse(listAdapter(data));
            }

            @Override
            public void onErrorCodeAnswer(int code) {
                Log.e(LOG_TAG, "--- Error code " + code);
                listener.errorResponse("Error code" + code);
            }

            @Override
            public void onError(String string) {
                Log.e(LOG_TAG, "--- Error " + string);
            }

            private List<Bond> listAdapter(List<EntityBond> data) {
                List<Bond> bondList = new ArrayList<Bond>(data.size());
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        for (EntityBond entity : data) {
                            bondList.add(DomainConverter.bondRESTModelConverter(entity));
                        }
                    }
                });
                t.start();
                return bondList;
            }

        }).getData();

    }

}
