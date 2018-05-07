package hu.herold.mobsoft.recipher.interactor.recipes.event;

import java.util.List;

import hu.herold.mobsoft.recipher.interactor.base.EventBase;
import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 05. 06..
 */

public class GetRecipesEvent extends EventBase {

    private List<Recipe> recipes;

    public GetRecipesEvent() {}

    public GetRecipesEvent(int code, List<Recipe> recipes, Throwable throwable) {
        this.code = code;
        this.recipes = recipes;
        this.throwable = throwable;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
