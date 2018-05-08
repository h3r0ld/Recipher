package hu.herold.mobsoft.recipher.interactor.recipes;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.interactor.recipes.event.DeleteRecipeEvent;
import hu.herold.mobsoft.recipher.interactor.recipes.event.GetRecipeDetailsEvent;
import hu.herold.mobsoft.recipher.interactor.recipes.event.GetRecipesEvent;
import hu.herold.mobsoft.recipher.interactor.recipes.event.SaveRecipeEvent;
import hu.herold.mobsoft.recipher.network.api.RecipeApi;
import hu.herold.mobsoft.recipher.network.model.GetResponse;
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

    public  void getRecipeDetails(String id) {
        Call<GetResponse> recipeByIdCall = recipeApi.getRecipeById(id);

        GetRecipeDetailsEvent event = new GetRecipeDetailsEvent();
        try {
            Response<GetResponse> recipeResponse = recipeByIdCall.execute();

            if (recipeResponse.code() != 200) {
                throw new Exception("Result code is not 200, code is: " + recipeResponse.code());
            }

            event.setCode(recipeResponse.code());
            event.setRecipe(recipeResponse.body().getRecipe());
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            EventBus.getDefault().post(event);
        }
    }

    public void saveRecipe(Recipe recipe) {

        SaveRecipeEvent event = new SaveRecipeEvent();

        try {
            recipeRepository.saveRecipe(recipe);
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            EventBus.getDefault().post(event);
        }
    }

    public void deleteRecipe(String id) {
        DeleteRecipeEvent event = new DeleteRecipeEvent();

        try {
            recipeRepository.deleteRecipeById(id);
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            EventBus.getDefault().post(event);
        }
    }
}