package hu.herold.mobsoft.recipher.ui.favourites;


import java.util.List;

import hu.herold.mobsoft.recipher.client.model.Recipe;

/**
 * Created by herold on 2018. 03. 23..
 */

public interface FavouritesScreen {

    void showFavouriteRecipes(List<Recipe> recipes);
}
