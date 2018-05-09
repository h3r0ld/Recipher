package hu.herold.mobsoft.recipher.ui.recipes.details;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.interactor.favourites.FavouritesInteractor;
import hu.herold.mobsoft.recipher.interactor.recipes.event.DeleteRecipeEvent;
import hu.herold.mobsoft.recipher.interactor.recipes.event.GetRecipeDetailsEvent;
import hu.herold.mobsoft.recipher.interactor.recipes.event.GetRecipesEvent;
import hu.herold.mobsoft.recipher.interactor.recipes.event.SaveRecipeEvent;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.interactor.recipes.RecipesInteractor;
import hu.herold.mobsoft.recipher.ui.Presenter;

/**
 * Created by herold on 2018. 03. 23..
 */

public class RecipeDetailsPresenter extends Presenter<RecipeDetailsScreen> {

    @Inject
    RecipesInteractor recipeInteractor;

    @Inject
    Executor executor;

    public RecipeDetailsPresenter() {
        RecipherApplication.injector.inject(this);
    }

    @Override
    public void attachScreen(RecipeDetailsScreen screen) {
        EventBus.getDefault().register(this);
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void getRecipeDetails(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeInteractor.getRecipeDetails(recipe);
            }
        });
    }

    public void saveFavouriteRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeInteractor.saveRecipe(recipe);
            }
        });
    }

    public void deleteFavouriteRecipe(final String id){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeInteractor.deleteRecipe(id);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleGetRecipeDetailsEvent(final GetRecipeDetailsEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showRecipe(event.getRecipe());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleSaveRecipeEvent(final SaveRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            if (screen != null) {
                screen.showMessage(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.savedRecipe();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleDeleteRecipeEvent(final DeleteRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            if (screen != null) {
                screen.showMessage(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.deletedRecipe();
            }
        }
    }
}
