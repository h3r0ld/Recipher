package hu.herold.mobsoft.recipher.ui.main.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hu.herold.mobsoft.recipher.ui.about.AboutFragment;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesFragment;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesFragment;

/**
 * Created by herold on 2018. 04. 27..
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RecipesFragment();
            case 1:
                return new FavouritesFragment();
            case 2:
                return new AboutFragment();
            default:
                return new AboutFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}
