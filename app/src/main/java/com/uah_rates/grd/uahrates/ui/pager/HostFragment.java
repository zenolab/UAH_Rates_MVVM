package com.uah_rates.grd.uahrates.ui.pager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.uah_rates.grd.uahrates.R;

public class HostFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public HostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_host, container, false);

        tabLayout = (TabLayout)rootView.findViewById(R.id.tabs);
        viewPager = (ViewPager)rootView.findViewById(R.id.view_pager);

        viewPager.setAdapter(new CustomFragmentPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

}
