package hu.herold.mobsoft.recipher.ui.favourites.details;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.interactor.favourites.FavouritesInteractor;
import hu.herold.mobsoft.recipher.interactor.favourites.event.DeleteFavouriteEvent;
import hu.herold.mobsoft.recipher.interactor.favourites.event.GetFavouriteDetailsEvent;
import hu.herold.mobsoft.recipher.interactor.favourites.event.SaveFavouriteEvent;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.ui.Presenter;

/**
 * Created by herold on 2018. 05. 08..
 */

public class FavouriteDetailsPresenter extends Presenter<FavouriteDetailsScreen> {

    @Inject
    FavouritesInteractor favouritesInteractor;

    @Inject
    Executor executor;

    public FavouriteDetailsPresenter() {
        RecipherApplication.injector.inject(this);
    }

    @Override
    public void attachScreen(FavouriteDetailsScreen screen) {
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
                favouritesInteractor.getFavouriteRecipeById(recipe.getRecipeId());
            }
        });
    }

    public void saveRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                favouritesInteractor.saveFavouriteRecipe(recipe);
            }
        });
    }

    public void deleteRecipe(final String id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                favouritesInteractor.deleteFavoriteRecipe(id);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleGetFavouriteDetailsEvent(final GetFavouriteDetailsEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            if (screen != null) {
                screen.showMessage(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showRecipe(event.getRecipe());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleSaveRecipeEvent(final SaveFavouriteEvent event) {
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
    public void handleDeleteRecipeEvent(final DeleteFavouriteEvent event) {
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
