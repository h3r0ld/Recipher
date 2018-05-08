package hu.herold.mobsoft.recipher.ui.recipes.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesAdapter;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsScreen {

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
    @BindView(R.id.fabSave)
    FloatingActionButton fabSave;
    @BindView(R.id.fabActionMenu)
    FloatingActionMenu fabActionMenu;
    @BindView(R.id.fabDelete)
    FloatingActionButton fabDelete;

    private Recipe recipe;
    private List<String> ingredients;
    private IngredientsAdapter ingredientsAdapter;

    public RecipeDetailsActivity() {
        RecipherApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        ingredientRecyclerView.setLayoutManager(llm);

        ingredients = new ArrayList<>();

        ingredientsAdapter = new IngredientsAdapter(ingredients);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);

        fabActionMenu.removeMenuButton(fabSave);
        fabActionMenu.removeMenuButton(fabDelete);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recipeDetailsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        recipeDetailsPresenter.detachScreen();
    }

    @Override
    public void onResume() {
        super.onResume();
        setVisibility(false, false, true);

        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey(RecipesAdapter.RECIPE)) {
            String json = intent.getExtras().getString(RecipesAdapter.RECIPE);
            Recipe recipe = (new Gson()).fromJson(json, Recipe.class);
            recipeDetailsPresenter.getRecipeDetails(recipe);
        } else {
            setVisibility(false, false, false);
            showMessage("No data found.");
        }
    }

    @OnClick(R.id.fabSave)
    public void onSaveClicked() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//
//        final EditText input = new EditText(this);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        input.setLayoutParams(lp);
//
//        alertDialogBuilder.setTitle(R.string.encryptionTitle);
//        alertDialogBuilder
//                .setMessage(R.string.encryptionText)
//                .setCancelable(false)
//                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        ((RecipeDetailsActivity) getApplicationContext()).finish();
//                    }
//                })
//                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.setView(input);
//        alertDialog.show();

        recipeDetailsPresenter.saveFavouriteRecipe(recipe);
    }

    @OnClick(R.id.fabDelete)
    public void onDeleteClicked() {
        recipeDetailsPresenter.deleteFavouriteRecipe(recipe.getRecipeId());
    }

    @Override
    public void showNetworkError(String message) {
        setVisibility(false, false, false);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void deletedRecipe() {
        fabActionMenu.close(true);

        fabActionMenu.removeMenuButton(fabDelete);
        fabActionMenu.addMenuButton(fabSave);

        Snackbar.make(fabActionMenu, "Removed from favourites.", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recipeDetailsPresenter.saveFavouriteRecipe(recipe);
                    }
                }).show();
    }

    @Override
    public void savedRecipe() {
        fabActionMenu.close(true);

        fabActionMenu.removeMenuButton(fabSave);
        fabActionMenu.addMenuButton(fabDelete);

        Snackbar.make(fabActionMenu, "Added to favourites.", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recipeDetailsPresenter.deleteFavouriteRecipe(recipe.getRecipeId());
                    }
                }).show();
    }

    @Override
    public void showRecipe(Recipe recipe) {

        if (recipe == null) {
            setVisibility(false, false, false);
            showMessage("No recipe details.");
            return;
        }

        this.recipe = recipe;

        ingredients.clear();
        ingredients.addAll(recipe.getIngredients());
        ingredientsAdapter.notifyDataSetChanged();


        if (recipe.getImageUrl() != null) {
            Glide.with(getApplicationContext()).load(recipe.getImageUrl()).into(pictImageView);
        }

        titleTextView.setText(recipe.getTitle());
        if (recipe.getSocialRank() != null) {
            scoreTextView.setText(recipe.getSocialRank().toString());
        }
        descriptionEditText.setText("No description is available. \nFill it, then add the recipe to your favourites to keep the changes!");

        if (recipe.getFavourite()) {
            fabActionMenu.addMenuButton(fabDelete);
        } else {
            fabActionMenu.addMenuButton(fabSave);
        }

        setVisibility(true, true, false);
    }

    private void setVisibility(boolean layoutVisible, boolean fabVisible, boolean progressBarVisible) {
        linearLayout.setVisibility(layoutVisible ? View.VISIBLE : View.GONE);
        fabActionMenu.setVisibility(fabVisible ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(progressBarVisible ? View.VISIBLE : View.GONE);
    }


}
