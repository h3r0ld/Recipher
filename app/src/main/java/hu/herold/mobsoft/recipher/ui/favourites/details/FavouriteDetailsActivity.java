package hu.herold.mobsoft.recipher.ui.favourites.details;

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
import hu.herold.mobsoft.recipher.ui.recipes.details.IngredientsAdapter;

public class FavouriteDetailsActivity extends AppCompatActivity implements FavouriteDetailsScreen {

    @Inject
    FavouriteDetailsPresenter favouriteDetailsPresenter;

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
    @BindView(R.id.fabDelete)
    FloatingActionButton fabDelete;
    @BindView(R.id.fabActionMenu)
    FloatingActionMenu fabActionMenu;
    @BindView(R.id.titleEditText)
    EditText titleEditText;

    private Recipe recipe;
    private List<String> ingredients;
    private IngredientsAdapter ingredientsAdapter;


    public FavouriteDetailsActivity() {
        RecipherApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_details);
        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        ingredientRecyclerView.setLayoutManager(llm);

        ingredients = new ArrayList<>();

        ingredientsAdapter = new IngredientsAdapter(ingredients);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        favouriteDetailsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        favouriteDetailsPresenter.detachScreen();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setVisibility(false, false, true);

        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey(RecipesAdapter.RECIPE)) {
            String json = intent.getExtras().getString(RecipesAdapter.RECIPE);
            Recipe recipe = (new Gson()).fromJson(json, Recipe.class);
            favouriteDetailsPresenter.getRecipeDetails(recipe);
        } else {
            setVisibility(false, false, false);
            showMessage("No data found.");
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void deletedRecipe() {
        fabActionMenu.close(true);

        Snackbar.make(fabActionMenu, "Removed from favourites.", Snackbar.LENGTH_LONG)
                .show();

        finish();
    }

    @Override
    public void savedRecipe() {
        fabActionMenu.close(true);

        Snackbar.make(fabActionMenu, "Saved Changes.", Snackbar.LENGTH_LONG)
                .show();
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

        titleEditText.setText(recipe.getTitle());

        titleTextView.setText("Publisher: " + recipe.getPublisher());

        if (recipe.getSocialRank() != null) {
            scoreTextView.setText(recipe.getSocialRank().toString());
        }
        descriptionEditText.setText(recipe.getDescription());

        setVisibility(true, true, false);
    }

    @OnClick(R.id.fabDelete)
    public void onDeleteClicked() {
        favouriteDetailsPresenter.deleteRecipe(recipe.getRecipeId());
    }

    @OnClick(R.id.fabSave)
    public void onSaveClicked() {
        recipe.setDescription(descriptionEditText.getText().toString());
        favouriteDetailsPresenter.saveRecipe(recipe);
    }

    protected void setVisibility(boolean layoutVisible, boolean fabVisible, boolean progressBarVisible) {
        linearLayout.setVisibility(layoutVisible ? View.VISIBLE : View.GONE);
        fabActionMenu.setVisibility(fabVisible ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(progressBarVisible ? View.VISIBLE : View.GONE);
    }

}
