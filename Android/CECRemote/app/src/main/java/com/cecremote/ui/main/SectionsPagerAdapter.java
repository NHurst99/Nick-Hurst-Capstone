package com.cecremote.ui.main;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.cecremote.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[] {R.string.tab_text_1,  R.string.tab_text_2, R.string.tab_text_3,R.string.tab_text_4,R.string.tab_text_5, R.string.tab_text_6, R.string.tab_text_7 };
    private final Context mContext;
    private int count = 1;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        SharedPreferences settings = mContext.getSharedPreferences("options", 0);
        String fragmentOptions = settings.getString("options"+position, "");
        return PlaceholderFragment.newInstance(position , fragmentOptions);
//        return PlaceholderFragment.newInstance(position + 1, "poweronoffnavigation");
    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mContext.getResources().getString(TAB_TITLES[position]);
//    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return count;
    }

    public void setCount(int count){
        this.count = count;
        this.notifyDataSetChanged();
    }
}