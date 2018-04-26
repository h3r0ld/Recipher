package hu.herold.mobsoft.recipher.interactor.recipes;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.network.api.RecipeApi;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.db.repository.RecipeRepository;

/**
 * Created by herold on 2018. 03. 23..
 */

public class RecipesInteractor {

    @Inject
    RecipeRepository recipeRepository;

    @Inject
    RecipeApi recipeApi;

    public RecipesInteractor() {
        RecipherApplication.injector.inject(this);
    }

    public void getRecipes(String title, String mainIngredient) {

    }

    public  void getRecipeDetails(long id) {

    }

    public void saveRecipe(Recipe recipe) {

    }

    public void deleteRecipe(long id) {

    }
}