package com.uah_rates.grd.uahrates.data.repository.bond;

import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.data.repository.RepositoryCallbackListener;

public interface BondRepository {

    void getRepositoryListener(RepositoryCallbackListener asyncListener, final EntityBond entityBond);
}

