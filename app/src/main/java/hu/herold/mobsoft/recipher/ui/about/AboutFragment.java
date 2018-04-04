package hu.herold.mobsoft.recipher.ui.about;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.RecipherApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements AboutScreen {

    @Inject
    AboutPresenter aboutPresenter;

    public AboutFragment() {
        // Required empty public constructor
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

        aboutPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        aboutPresenter.detachScreen();
    }
}