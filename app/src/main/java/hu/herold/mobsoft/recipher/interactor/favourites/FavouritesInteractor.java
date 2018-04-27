package hu.herold.mobsoft.recipher.interactor.favourites;

import java.util.List;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.db.repository.RecipeRepository;

/**
 * Created by herold on 2018. 03. 23..
 */

public class FavouritesInteractor {

    @Inject
    RecipeRepository recipeRepository;

    public FavouritesInteractor() {
        RecipherApplication.injector.inject(this);
    }

    public void getFavouriteRecipes(String title, List<String> ingredients) {

    }

    public void getFavouriteRecipeById(String id) {

    }
}
