package hu.herold.mobsoft.recipher.ui.recipes;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.interactor.recipes.RecipesInteractor;
import hu.herold.mobsoft.recipher.ui.Presenter;

/**
 * Created by herold on 2018. 03. 23..
 */

public class RecipesPresenter extends Presenter<RecipesScreen> {

    @Inject
    private RecipesInteractor recipesInteractor;


    public void refreshRecipes() {

    }
}
