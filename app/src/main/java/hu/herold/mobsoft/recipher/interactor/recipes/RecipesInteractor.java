package hu.herold.mobsoft.recipher.interactor.recipes;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;
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
        GetRecipesEvent event = new GetRecipesEvent();

        try {
            Call<SearchResponse> searchResponseCall = recipeApi.searchRecipes(recipeFilter, "", 1);

            Response<SearchResponse> searchResponse = searchResponseCall.execute();

            if (searchResponse.code() != 200) {
                throw new Exception("Result code is not 200:  " + searchResponse.code());
            }

            Map<String, Boolean> recipeIds = recipeRepository.getRecipeIds();

            for (Recipe recipe : searchResponse.body().getRecipes()) {

                recipe.setFavourite(recipeIds.containsKey(recipe.getRecipeId()));

                if (recipe.getFavourite()) {
                    recipe.setIsProtected(recipeIds.get(recipe.getRecipeId()));
                }
            }

            event.setCode(searchResponse.code());
            event.setRecipes(searchResponse.body().getRecipes());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public  void getRecipeDetails(Recipe recipe) {
        GetRecipeDetailsEvent event = new GetRecipeDetailsEvent();

        try {
            Call<GetResponse> recipeByIdCall = recipeApi.getRecipeById(recipe.getRecipeId());
            Response<GetResponse> recipeResponse = recipeByIdCall.execute();

            if (recipeResponse.code() != 200) {
                throw new Exception("Result code is not 200, code is: " + recipeResponse.code());
            }

            recipeResponse.body().getRecipe().setFavourite(recipe.getFavourite());

            event.setCode(recipeResponse.code());
            event.setRecipe(recipeResponse.body().getRecipe());
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            EventBus.getDefault().post(event);
        }
    }

    public void saveRecipe(Recipe recipe, String password) {

        SaveRecipeEvent event = new SaveRecipeEvent();

        try {
            recipeRepository.saveRecipe(recipe, password);
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