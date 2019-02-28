package com.uah_rates.grd.uahrates.network;

import java.util.List;

import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.data.entity.EntityRate;
import com.uah_rates.grd.uahrates.domain.model.pojo.Rate;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("NBUStatService/v1/statdirectory/exchange?json")
    Call<List <EntityRate> > fetchRates2();

    @GET("NBUStatService/v1/statdirectory/ovdp?json")
    Call<List <EntityBond> > fetchBonds();

}
