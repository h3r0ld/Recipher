package hu.herold.mobsoft.recipher.ui.recipes;

import java.util.List;

import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 03. 23..
 */

public interface RecipesScreen {

    void showRecipes(List<Recipe> recipes);

    void showNetworkError(String errorMsg);
}
