package hu.herold.mobsoft.recipher.ui.favourites.password;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.interactor.favourites.FavouritesInteractor;
import hu.herold.mobsoft.recipher.interactor.favourites.event.IsValidPasswordEvent;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.ui.Presenter;

/**
 * Created by herold on 2018. 05. 12..
 */

public class PasswordProtectedPresenter extends Presenter<PasswordProtectedScreen> {

    @Inject
    FavouritesInteractor favouritesInteractor;

    @Inject
    Executor executor;

    public PasswordProtectedPresenter() {
        RecipherApplication.injector.inject(this);
    }

    @Override
    public void attachScreen(PasswordProtectedScreen screen) {
        EventBus.getDefault().register(this);
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void validatePassword(final Recipe recipe, final String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                favouritesInteractor.isValidPassword(recipe.getRecipeId(), password);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleIsValidPasswordEvent(final IsValidPasswordEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            if (screen != null) {
                screen.showValidationFailed(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {

                if (event.isValid()) {
                    screen.navigateToFavouriteRecipe();
                } else {
                    screen.showValidationFailed("Wrong password.");
                }
            }
        }
    }
}
