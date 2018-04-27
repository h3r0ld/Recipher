package hu.herold.mobsoft.recipher.ui.recipes;


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
public class RecipesFragment extends Fragment implements RecipesScreen {

    @Inject
    RecipesPresenter recipesPresenter;

    public RecipesFragment() {
        // Required empty public constructor
        RecipherApplication.injector.inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        recipesPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        recipesPresenter.detachScreen();
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {

    }

    @Override
    public void showNetworkError(String errorMsg) {

    }
}
