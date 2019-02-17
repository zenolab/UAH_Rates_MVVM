package com.uah_rates.grd.uahrates.domain.interactor.usecase;

import com.uah_rates.grd.uahrates.domain.interactor.model.pojo.Bond;
import com.uah_rates.grd.uahrates.presentation.ui.viewmodel.BondUseCaseListener;

import java.util.List;

public interface BondUseCase {

   // public List< Bond> getDomainListener(BondUseCaseListener bondUseCaseListener,List<Bond> list);
    public void getDomainListener(BondUseCaseListener bondUseCaseListener);
}
