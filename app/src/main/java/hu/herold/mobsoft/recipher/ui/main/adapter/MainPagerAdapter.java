package hu.herold.mobsoft.recipher.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import hu.herold.mobsoft.recipher.ui.about.AboutFragment;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesFragment;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesFragment;

/**
 * Created by herold on 2018. 04. 27..
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public MainPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);

        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
