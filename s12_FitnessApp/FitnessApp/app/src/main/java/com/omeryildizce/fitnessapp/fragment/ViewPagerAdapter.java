package com.omeryildizce.fitnessapp.fragment;




import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;



public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment selectedFragment;

        switch (position) {
            case 0:
                selectedFragment = FitnessPictures.newInstance();
                break;
            case 1:
                selectedFragment = Details.newInstance();
                break;
            default:
                return null;
        }

        return selectedFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence selectedTitle;

        switch (position) {
            case 0:
                selectedTitle = "Fitness Pictures";
                break;
            case 1:
                selectedTitle = "Details";
                break;
            default:
                return null;

        }
        return selectedTitle;
    }
}
