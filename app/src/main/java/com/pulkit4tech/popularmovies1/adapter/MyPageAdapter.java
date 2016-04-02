package com.pulkit4tech.popularmovies1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pulkit4tech.popularmovies1.data.Data_item;
import com.pulkit4tech.popularmovies1.fragement.Reviews;
import com.pulkit4tech.popularmovies1.fragement.Synopsis;
import com.pulkit4tech.popularmovies1.fragement.Trailers;

/**
 * Created by PULKIT on 4/2/2016.
 */
public class MyPageAdapter extends FragmentStatePagerAdapter {

    Data_item data;
    public MyPageAdapter(FragmentManager fm , Data_item data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fg = null;
        switch (position){
            case 0 : fg = new Synopsis();break;
            case 1 : fg = new Trailers();break;
            case 2 : fg = new Reviews();break;
        }

        return fg;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Synopsis";
                break;
            case 1:
                title="Trailer";
                break;
            case 2:
                title="Reviews";
                break;
        }

        return title;
    }
}
