package hu.herold.mobsoft.recipher.ui.favourites.details;

import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 05. 08..
 */

public interface FavouriteDetailsScreen {

    void showMessage(String message);

    void deletedRecipe();

    void savedRecipe();

    void showRecipe(Recipe recipe);
}
