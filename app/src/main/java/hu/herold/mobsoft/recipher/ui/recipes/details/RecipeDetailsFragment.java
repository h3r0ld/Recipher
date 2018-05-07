package hu.herold.mobsoft.recipher.ui.recipes.details;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailsFragment extends Fragment implements RecipeDetailsScreen {

    @Inject
    RecipeDetailsPresenter recipeDetailsPresenter;

    @BindView(R.id.pictImageView)
    ImageView pictImageView;
    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @BindView(R.id.scoreTextView)
    TextView scoreTextView;
    @BindView(R.id.ingredientRecyclerView)
    RecyclerView ingredientRecyclerView;
    @BindView(R.id.descriptionEditText)
    EditText descriptionEditText;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    Unbinder unbinder;

    private Recipe recipe;

    public RecipeDetailsFragment() {
        RecipherApplication.injector.inject(this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        unbinder = ButterKnife.bind(this, view);

        ingredientRecyclerView.setAdapter(new IngredientsAdapter(recipe.getIngredients()));
        return view;
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
    public void onResume() {
        super.onResume();
        setProgressBarVisible(true);

        String recipeId = null;

        if (getArguments().containsKey(RecipesAdapter.RECIPE_ID)) {
            recipeId = getArguments().getString(RecipesAdapter.RECIPE_ID);
        }
        recipeDetailsPresenter.getRecipeDetails(recipeId);
    }

    @Override
    public void saveFavouriteRecipe() {
        recipeDetailsPresenter.saveFavouriteRecipe(recipe);
    }

    @Override
    public void deleteFavouriteRecipe() {
        recipeDetailsPresenter.deleteFavouriteRecipe(recipe.getRecipeId());
    }

    @Override
    public void showNetworkError(String message) {
        linearLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);

        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRecipe(Recipe recipe) {
        this.recipe = recipe;

        if (recipe.getImageUrl() != null) {
            Glide.with(getContext()).load(recipe.getImageUrl()).into(pictImageView);
        }

        titleTextView.setText(recipe.getTitle());
        scoreTextView.setText(recipe.getSocialRank().toString());
        descriptionEditText.setText("No description is available.");

        setProgressBarVisible(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setProgressBarVisible(boolean progressBarVisible) {
        linearLayout.setVisibility(progressBarVisible ? View.GONE : View.VISIBLE);
        fab.setVisibility(progressBarVisible ? View.GONE : View.VISIBLE);
        progressBar.setVisibility(progressBarVisible ? View.VISIBLE : View.GONE);
    }
}
