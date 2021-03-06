package hu.herold.mobsoft.recipher.ui.about;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.ui.ViewPagerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements AboutScreen, ViewPagerFragment {

    @Inject
    AboutPresenter aboutPresenter;

    @Inject
    Tracker tracker;

    public AboutFragment() {
        RecipherApplication.injector.inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        String env = System.getenv("FOOD2FORK_API_KEY");

        aboutPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        aboutPresenter.detachScreen();
    }

    @Override
    public void onSwitchedTo() {
        tracker.setScreenName(AboutScreen.class.toString());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onResume() {
        super.onResume();
        tracker.setScreenName(AboutScreen.class.toString());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}
