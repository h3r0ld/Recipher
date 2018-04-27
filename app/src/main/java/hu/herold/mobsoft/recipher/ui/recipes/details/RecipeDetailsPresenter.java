package hu.herold.mobsoft.recipher.ui.recipes.details;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.interactor.recipes.RecipesInteractor;
import hu.herold.mobsoft.recipher.ui.Presenter;

/**
 * Created by herold on 2018. 03. 23..
 */

public class RecipeDetailsPresenter extends Presenter<RecipeDetailsScreen> {

    @Inject
    RecipesInteractor recipeInteractor;

    public void saveFavouriteRecipe(Recipe recipe) {

    }

    public void deleteFavouriteRecipe(long id){

    }
}
