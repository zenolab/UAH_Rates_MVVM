package com.uah_rates.grd.uahrates.presentation.ui.viewmodel;

import com.uah_rates.grd.uahrates.domain.model.pojo.Bond;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface PresentationListener {

    void successfulResponse(Collection<?> collection);

    void errorResponse(String error);

}
