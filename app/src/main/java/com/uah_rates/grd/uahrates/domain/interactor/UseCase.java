package com.uah_rates.grd.uahrates.domain.interactor;

import com.uah_rates.grd.uahrates.presentation.ui.viewmodel.PresentationListener;

public interface UseCase {

    public  abstract <L> void setDomainListener(L presentationListener);
}
