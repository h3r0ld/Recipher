package hu.herold.mobsoft.recipher.ui.recipes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.RecipherApplicationComponent;
import hu.herold.mobsoft.recipher.interactor.recipes.RecipesInteractor;
import hu.herold.mobsoft.recipher.interactor.recipes.event.GetRecipesEvent;
import hu.herold.mobsoft.recipher.ui.Presenter;

/**
 * Created by herold on 2018. 03. 23..
 */

public class RecipesPresenter extends Presenter<RecipesScreen> {

    @Inject
    RecipesInteractor recipesInteractor;

    @Inject
    Executor executor;

    public RecipesPresenter() {
        RecipherApplication.injector.inject(this);
    }

    @Override
    public void attachScreen(RecipesScreen screen) {
        EventBus.getDefault().register(this);
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void refreshRecipes(final String recipeFilter) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.getRecipes(recipeFilter);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleGetRecipesEvent(final GetRecipesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showRecipes(event.getRecipes());
            }
        }

    }
}
