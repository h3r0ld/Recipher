package hu.herold.mobsoft.recipher.ui.recipes.details;

import java.util.List;

import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 03. 23..
 */

public interface RecipeDetailsScreen {

    void deletedRecipe();

    void savedRecipe();

    void showRecipe(Recipe recipe);

    void showNetworkError(String message);

    void showMessage(String message);
}
