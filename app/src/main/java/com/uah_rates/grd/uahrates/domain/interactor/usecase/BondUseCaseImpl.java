package com.uah_rates.grd.uahrates.domain.interactor.usecase;

import android.util.Log;
import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.data.repository.RepositoryCallbackListener;
import com.uah_rates.grd.uahrates.data.repository.bond.BondRepositoryImpl;
import com.uah_rates.grd.uahrates.data.repository.rate.RateRepositoryImpl;
import com.uah_rates.grd.uahrates.domain.interactor.DomainConverter;
import com.uah_rates.grd.uahrates.domain.interactor.model.pojo.Bond;
import com.uah_rates.grd.uahrates.presentation.ui.viewmodel.BondUseCaseListener;

import java.util.ArrayList;
import java.util.List;

public class BondUseCaseImpl implements BondUseCase {

    private static final String LOG_TAG = RateRepositoryImpl.class.getSimpleName();

    BondUseCaseListener bondUseCaseListener;

    public BondUseCaseImpl() {

        getData();


    }


    @Override
    public void getDomainListener(BondUseCaseListener bondUseCaseListener) {
            this.bondUseCaseListener = bondUseCaseListener;
    }

    private void getData() {

        new RepositoryCallbackListener() {

            @Override
            public void onSuccessAnswer(List<EntityBond> data) {
                Log.e(LOG_TAG, "--- onSuccessAnswer ");

                //--------------CONVERT LIST-----------
//                List<String> listStrings =new ArrayList<>();
//                List<Integer> listIntegers = new ArrayList<Integer>(listStrings.size());
//                for(String current:listStrings){
//                    listIntegers.add(Integer.parseInt(current));
//                }
                //----------------Adapter (from data to domain model)--------------------
                List<Bond> bondList = new ArrayList<Bond>(data.size());
                for(EntityBond current:data){
                    bondList.add(DomainConverter.bondRESTModelConverter(current));
                }

                //------------------------------------
                bondUseCaseListener.successfulResponse(bondList);

                // Bond list =DomainConverter.bondRESTModelConverter(data);
                // getDomainListener();
            }

            @Override
            public void onErrorCodeAnswer(int code) {
                Log.e(LOG_TAG, "--- Error code ");
                bondUseCaseListener.errorResponse("Error mapping");
            }

            @Override
            public void onErrorАnswer(String string) {
                Log.e(LOG_TAG, "--- Error "+string);
            }
        };

        new BondRepositoryImpl().getRepositoryListener((RepositoryCallbackListener) this,new EntityBond());
    }



}
