package com.uah_rates.grd.uahrates.data.repository;

import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.domain.interactor.model.pojo.Bond;

import java.util.List;

public interface RepositoryCallbackListener {

   // void onSuccessAnswer(List<Bond> data);

    void onSuccessAnswer(List<EntityBond> body);

    void onErrorCodeAnswer(int code);

    void onErrorАnswer(String string);


}
