package com.uah_rates.grd.uahrates.presentation.ui.viewmodel;

import com.uah_rates.grd.uahrates.domain.interactor.model.pojo.Bond;

import java.util.List;

public interface BondUseCaseListener {

    void successfulResponse(List<Bond> bondList);

    void errorResponse(String error);

}
