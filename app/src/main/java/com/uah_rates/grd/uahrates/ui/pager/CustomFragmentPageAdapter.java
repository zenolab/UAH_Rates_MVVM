package com.uah_rates.grd.uahrates.ui.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.uah_rates.grd.uahrates.ui.screens.CurrencyFragmentView;
import com.uah_rates.grd.uahrates.ui.screens.MetalFragmentView;


public class CustomFragmentPageAdapter extends FragmentPagerAdapter {
    private static final String LOG_TAG = CustomFragmentPageAdapter.class.getSimpleName();
    final int PAGE_COUNT = 2;
    private String[] tabTitles = {"Currencies", "Metals"};

    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CurrencyFragmentView();
            case 1:
                return new MetalFragmentView();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}