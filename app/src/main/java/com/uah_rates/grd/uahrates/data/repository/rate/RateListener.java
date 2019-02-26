package com.uah_rates.grd.uahrates.data.repository.rate;

import com.uah_rates.grd.uahrates.data.entity.EntityRate;
import com.uah_rates.grd.uahrates.data.repository.DataCallbackListener;

import java.util.List;

public interface RateListener extends DataCallbackListener {

    void onSuccessAnswerOfRate(List<EntityRate> data);
}
