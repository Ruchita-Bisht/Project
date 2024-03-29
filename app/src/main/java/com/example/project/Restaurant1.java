package com.example.project;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Restaurant1 extends Fragment {
private AppBarLayout appBarLayout;
private TabLayout tabLayout;
private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_restaurant1, container, false);

        appBarLayout=v.findViewById(R.id.appbar);
        tabLayout=v.findViewById(R.id.tablayout);
        viewPager=v.findViewById(R.id.viewpage);
        ViewPage_Adapter adapter=new ViewPage_Adapter(getFragmentManager());
        adapter.addFragment(new Information(),"information");
        adapter.addFragment(new Contact(),"Contact");
        adapter.addFragment(new Album(),"Album");
        adapter.addFragment(new Feedback(),"Feedback");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

}
