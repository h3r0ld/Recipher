package hu.herold.mobsoft.recipher.interactor.favourites;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;
import hu.herold.mobsoft.recipher.db.mapper.Mapper;
import hu.herold.mobsoft.recipher.db.repository.RecipeRepository;
import hu.herold.mobsoft.recipher.interactor.base.EventBase;
import hu.herold.mobsoft.recipher.interactor.favourites.event.GetFavouritesEvent;
import hu.herold.mobsoft.recipher.interactor.recipes.event.GetRecipesEvent;
import hu.herold.mobsoft.recipher.network.model.Recipe;

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
        GetFavouritesEvent event = new GetFavouritesEvent();

        try {
            List<RecipeEntity> recipeEntities = recipeRepository.getRecipes(title);

            List<Recipe> recipes = new ArrayList<>();

            for (RecipeEntity entity: recipeEntities) {
                recipes.add(Mapper.mapRecipe(entity));
            }

            event.setRecipes(recipes);
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            EventBus.getDefault().post(event);
        }
    }

    public void getFavouriteRecipeById(String id) {

    }
}
