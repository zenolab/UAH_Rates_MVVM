package com.uah_rates.grd.uahrates.domain.interactor;

import android.util.Log;
import com.uah_rates.grd.uahrates.data.entity.EntityRate;
import com.uah_rates.grd.uahrates.data.repository.rate.RateListener;
import com.uah_rates.grd.uahrates.data.repository.rate.RateRepository;
import com.uah_rates.grd.uahrates.domain.DomainConverter;
import com.uah_rates.grd.uahrates.domain.model.pojo.Rate;
import com.uah_rates.grd.uahrates.presentation.ui.viewmodel.PresentationListener;

import java.util.ArrayList;
import java.util.List;

public class RateUseCaseImpl implements UseCase {

    private static final String LOG_TAG = RateUseCaseImpl.class.getSimpleName();

    private PresentationListener presentationListener;

    public RateUseCaseImpl() {
    }

    @Override
    public <L> void setDomainListener(L listener) {
        this.presentationListener = (PresentationListener) listener;
        getRepository();
    }

    private void getRepository() {
        new RateRepository(new RateListener() {


            @Override
            public void onSuccessAnswerOfRate(List<EntityRate> data) {
                presentationListener.successfulResponse(listAdapter((List<EntityRate>) data));
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

            private List<Rate> listAdapter(List<EntityRate> data) {
                List<Rate> rateList = new ArrayList<Rate>(data.size());
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        for (EntityRate entity : data) {
                            rateList.add(DomainConverter.rateRESTModelConverter(entity));
                        }
                    }
                });
                t.start();
                return rateList;
            }
        }).getData();

    }


}
