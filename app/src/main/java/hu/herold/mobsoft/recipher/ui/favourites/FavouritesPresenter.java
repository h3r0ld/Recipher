package hu.herold.mobsoft.recipher.ui.favourites;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.interactor.favourites.FavouritesInteractor;
import hu.herold.mobsoft.recipher.interactor.favourites.event.GetFavouritesEvent;
import hu.herold.mobsoft.recipher.interactor.recipes.event.GetRecipesEvent;
import hu.herold.mobsoft.recipher.ui.Presenter;

/**
 * Created by herold on 2018. 03. 23..
 */

public class FavouritesPresenter extends Presenter<FavouritesScreen> {

    @Inject
    FavouritesInteractor favouritesInteractor;

    @Inject
    Executor executor;

    public FavouritesPresenter() {
        RecipherApplication.injector.inject(this);
    }

    @Override
    public void attachScreen(FavouritesScreen screen) {
        EventBus.getDefault().register(this);
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void refreshFavouriteRecipes(final String title, final List<String> ingredients) {
        executor.execute(new Runnable() {
            @Override
            public void run() {favouritesInteractor.getFavouriteRecipes(title, ingredients);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleGetFavouritesEvent(final GetFavouritesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            if (screen != null) {
                screen.showMessage(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showFavouriteRecipes(event.getRecipes());
            }
        }

    }
}
