package com.uah_rates.grd.uahrates.data.repository;

import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.data.entity.EntityRate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface DataCallbackListener  {

    void onErrorCodeAnswer(int code);

    void onError(String string);


}
