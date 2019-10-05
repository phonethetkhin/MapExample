package com.example.mapexample.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mapexample.Fragments.AddFragment;
import com.example.mapexample.Fragments.ManageFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position)
        {
            case 0:
                fragment= new AddFragment();
                break;
            case 1:
                fragment=new ManageFragment();



        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
