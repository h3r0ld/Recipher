package hu.herold.mobsoft.recipher.interactor.favourites.event;

import java.util.List;

import hu.herold.mobsoft.recipher.interactor.base.EventBase;
import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 05. 08..
 */

public class GetFavouritesEvent extends EventBase {
    private List<Recipe> recipes;

    public GetFavouritesEvent() {
    }

    public GetFavouritesEvent(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
