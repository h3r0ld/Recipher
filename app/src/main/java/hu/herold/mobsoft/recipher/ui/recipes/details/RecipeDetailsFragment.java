package hu.herold.mobsoft.recipher.ui.recipes.details;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailsFragment extends Fragment implements RecipeDetailsScreen {

    @Inject
    private RecipeDetailsPresenter recipeDetailsPresenter;

    public RecipeDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_details, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        recipeDetailsPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        recipeDetailsPresenter.detachScreen();
    }

    @Override
    public void savedFavouriteRecipe() {

    }

    @Override
    public void deletedFavouriteRecipe() {

    }
}
