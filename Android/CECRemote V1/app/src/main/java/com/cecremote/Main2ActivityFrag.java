package com.cecremote;
//
//import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class Main2ActivityFrag extends FragmentStatePagerAdapter {
    public Main2ActivityFrag(FragmentManager fm) {
        super(fm);
    }


//    @Override
//    public Fragment getItem(int i) {
//        Fragment fragment = new DemoObjectFragment();
//        Bundle args = new Bundle();
//        // Our object is just an integer :-P
//        args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
//        fragment.setArguments(args);
//        return fragment;
//    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DemoObjectFragment();
            case 1:
                return new DemoObjectFragment();
            case 2:
                return new DemoObjectFragment();
            default:
                return null; // shouldn't happen
        }
    }
    private String[] tabTitles = new String[]{"Tab1", "Tab2", "Tab3"};

    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 5;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return "OBJECT " + (position + 1);
//    }


}

