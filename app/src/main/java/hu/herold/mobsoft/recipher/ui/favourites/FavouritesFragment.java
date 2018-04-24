package hu.herold.mobsoft.recipher.ui.favourites;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment implements FavouritesScreen {

    @Inject
    FavouritesPresenter favouritesPresenter;

    public FavouritesFragment() {
        // Required empty public constructor
        RecipherApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        favouritesPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        favouritesPresenter.detachScreen();
    }

    @Override
    public void showFavouriteRecipes(List<Recipe> recipes) {

    }
}
