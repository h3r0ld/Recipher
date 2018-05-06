package hu.herold.mobsoft.recipher.interactor.recipes.event;

import java.util.List;

import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 05. 06..
 */

public class GetRecipesEvent {

    private int code;
    private List<Recipe> recipes;
    private Throwable throwable;

    public GetRecipesEvent() {}

    public GetRecipesEvent(int code, List<Recipe> recipes, Throwable throwable) {
        this.code = code;
        this.recipes = recipes;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
