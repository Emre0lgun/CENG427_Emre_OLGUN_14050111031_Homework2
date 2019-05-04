package com.example.homework2;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            FoodList foodList = new FoodList();
            return foodList;
        }
        else if(position == 1){
            Announcement announcement = new Announcement();
            return announcement;
        }
        else if(position == 2){
            News news = new News();
            return news;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "FoodList";
        }else if (position == 1) {
            return "Announcements";
        }else if(position == 2){
            return "News";
        }
        return null;
    }
}
