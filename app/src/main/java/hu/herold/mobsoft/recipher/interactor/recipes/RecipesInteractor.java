package hu.herold.mobsoft.recipher.interactor.recipes;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.interactor.recipes.event.GetRecipesEvent;
import hu.herold.mobsoft.recipher.network.api.RecipeApi;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.db.repository.RecipeRepository;
import hu.herold.mobsoft.recipher.network.model.SearchResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by herold on 2018. 03. 23..
 */

public class RecipesInteractor {

    @Inject
    RecipeRepository recipeRepository;

    @Inject
    RecipeApi recipeApi;

    public RecipesInteractor() {
        RecipherApplication.injector.inject(this);
    }

    public void getRecipes(String recipeFilter) {
        Call<SearchResponse> searchResponseCall = recipeApi.searchRecipes(recipeFilter, "", 1);

        GetRecipesEvent event = new GetRecipesEvent();
        try {
            Response<SearchResponse> searchResponse = searchResponseCall.execute();

            if (searchResponse.code() != 200) {
                throw new Exception("Result code is not 200:  " + searchResponse.code());
            }

            event.setCode(searchResponse.code());
            event.setRecipes(searchResponse.body().getRecipes());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public  void getRecipeDetails(long id) {

    }

    public void saveRecipe(Recipe recipe) {

    }

    public void deleteRecipe(long id) {

    }
}