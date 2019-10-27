package com.cecremote;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Main2Activity2 extends Fragment {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    Main2ActivityFrag demoCollectionPagerAdapter;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main2, container, false);
        }

@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        demoCollectionPagerAdapter = new Main2ActivityFrag(getChildFragmentManager());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(demoCollectionPagerAdapter);
        }





}
