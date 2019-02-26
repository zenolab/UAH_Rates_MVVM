package com.uah_rates.grd.uahrates.data.repository.bond;

import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.data.repository.DataCallbackListener;

import java.util.List;

public interface BondListener extends DataCallbackListener {

    void onSuccessAnswerOfBond(List<EntityBond> data);

}
