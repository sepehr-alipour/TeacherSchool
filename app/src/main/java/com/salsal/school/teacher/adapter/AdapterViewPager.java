package com.salsal.school.teacher.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Taraabar on 12/2/2016.
 */

public class AdapterViewPager extends FragmentPagerAdapter {


    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public AdapterViewPager(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
    /*@Override
    public Fragment getItem(int pos) {
        switch (pos) {

            case 0:
                return FragmentMessages.newInstance(1);
            case 1:
                return FragmentHome.newInstance(2);
            case 2:
                return FragmentMessages.newInstance(3);

           *//* case 3:
                return FragmentMessages.newInstance(4);

            case 4:
                return FragmentProfileDetail.newInstance(5);*//*

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }*/

}
