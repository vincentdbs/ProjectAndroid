package com.android.projectandroid.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionMatchAdapter extends FragmentPagerAdapter {
    // This array list will gonna add the fragment one after another
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public SectionMatchAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }



    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
