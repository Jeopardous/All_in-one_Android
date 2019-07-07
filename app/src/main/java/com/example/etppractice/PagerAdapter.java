package com.example.etppractice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentList=new ArrayList<>();
    ArrayList<String> titleList=new ArrayList<>();

    public void addFragment(Fragment fragment,String title)
    {
        fragmentList.add(fragment);
        titleList.add(title);
    }

    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
