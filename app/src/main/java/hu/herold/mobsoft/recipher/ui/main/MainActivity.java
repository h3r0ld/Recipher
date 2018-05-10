package hu.herold.mobsoft.recipher.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.ui.ViewPagerFragment;
import hu.herold.mobsoft.recipher.ui.about.AboutFragment;
import hu.herold.mobsoft.recipher.ui.about.AboutScreen;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesFragment;
import hu.herold.mobsoft.recipher.ui.main.adapter.MainPagerAdapter;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesFragment;

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Inject
    MainPresenter mainPresenter;

    @Inject
    Tracker tracker;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private int[] tabIcons;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecipherApplication.injector.inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragments = new ArrayList<>();
        fragments.add(new RecipesFragment());
        fragments.add(new FavouritesFragment());
        fragments.add(new AboutFragment());

        viewPager.setAdapter(new MainPagerAdapter(this.getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ViewPagerFragment viewPagerFragment = (ViewPagerFragment) fragments.get(position);
                viewPagerFragment.onSwitchedTo();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tracker.setScreenName(MainScreen.class.toString());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private void setupTabIcons() {
        tabIcons = new int[] { R.drawable.ic_receipt_white_48dp, R.drawable.ic_stars_white_48dp, R.drawable.ic_info_white_48dp };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
}
