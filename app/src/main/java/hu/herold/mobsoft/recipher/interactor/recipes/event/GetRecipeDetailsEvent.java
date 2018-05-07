package hu.herold.mobsoft.recipher.interactor.recipes.event;

import java.util.List;

import hu.herold.mobsoft.recipher.interactor.base.EventBase;
import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 05. 08..
 */

public class GetRecipeDetailsEvent extends EventBase {

    private Recipe recipe;

    public GetRecipeDetailsEvent() {}

    public GetRecipeDetailsEvent(int code, Recipe recipe, Throwable throwable) {
        this.code = code;
        this.recipe = recipe;
        this.throwable = throwable;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
