package com.uah_rates.grd.uahrates.api;

import java.util.List;

import com.uah_rates.grd.uahrates.model.pojo.Bond;
import com.uah_rates.grd.uahrates.model.pojo.Rate;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitCallRateService {

    @GET("NBUStatService/v1/statdirectory/exchange?json")
    Call<List <Rate> > fetchRates();

    @GET("NBUStatService/v1/statdirectory/ovdp?json")
    Call<List <Bond> > fetchBonds();


}
