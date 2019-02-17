package com.uah_rates.grd.uahrates.domain.interactor.usecase;

import com.uah_rates.grd.uahrates.domain.interactor.model.pojo.Bond;

import java.util.List;

public interface BondUseCase {

    public List< Bond> getRepositoryListener(List<Bond> list);
}
