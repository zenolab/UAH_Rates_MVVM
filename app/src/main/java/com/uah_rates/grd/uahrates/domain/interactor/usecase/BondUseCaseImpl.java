package com.uah_rates.grd.uahrates.domain.interactor.usecase;

import android.util.Log;
import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.data.repository.RepositoryCallbackListener;
import com.uah_rates.grd.uahrates.data.repository.bond.BondRepositoryImpl;
import com.uah_rates.grd.uahrates.data.repository.rate.RateRepositoryImpl;
import com.uah_rates.grd.uahrates.domain.interactor.DomainConverter;
import com.uah_rates.grd.uahrates.domain.interactor.model.pojo.Bond;

import java.util.List;

public class BondUseCaseImpl implements BondUseCase {

    private static final String LOG_TAG = RateRepositoryImpl.class.getSimpleName();

    public BondUseCaseImpl() {

        new RepositoryCallbackListener() {

            @Override
            public void onSuccessAnswer(List<EntityBond> data) {
                Log.e(LOG_TAG, "--- onSuccessAnswer ");
              // Bond list =DomainConverter.bondRESTModelConverter(data);
               // getRepositoryListener();
            }

            @Override
            public void onErrorCodeAnswer(int code) {
                Log.e(LOG_TAG, "--- Error code ");
            }

            @Override
            public void onErrorАnswer(String string) {
                Log.e(LOG_TAG, "--- Error "+string);
            }
        };
         new BondRepositoryImpl().getRepositoryListener((RepositoryCallbackListener) this,new EntityBond());
    }


    @Override
    public List<Bond> getRepositoryListener(List<Bond> list) {
        return list;
    }
}
