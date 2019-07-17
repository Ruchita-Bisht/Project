package com.example.project;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

    public class ViewPage_Adapter extends FragmentPagerAdapter
    {
        private final List<Fragment> fragmentList=new ArrayList<>();
        private final List<String> fragmentListTitle =new ArrayList<>();

        public ViewPage_Adapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position)
        {
            return fragmentList.get(position);
        }
        @Override
        public int getCount()
        {
            return fragmentListTitle.size();
        }
        @Override
        public CharSequence getPageTitle(int position)
        {
            return fragmentListTitle.get(position);
        }
        public void addFragment(Fragment fragment, String Title) {
            fragmentList.add(fragment);
            fragmentListTitle.add(Title);
        }


    }


