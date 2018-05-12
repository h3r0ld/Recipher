package hu.herold.mobsoft.recipher;

import javax.inject.Singleton;

import dagger.Component;
import hu.herold.mobsoft.recipher.db.DbModule;
import hu.herold.mobsoft.recipher.interactor.InteractorModule;
import hu.herold.mobsoft.recipher.interactor.favourites.FavouritesInteractor;
import hu.herold.mobsoft.recipher.interactor.recipes.RecipesInteractor;
import hu.herold.mobsoft.recipher.network.NetworkModule;
import hu.herold.mobsoft.recipher.network.mock.MockRecipeApi;
import hu.herold.mobsoft.recipher.ui.UIModule;
import hu.herold.mobsoft.recipher.ui.about.AboutFragment;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesFragment;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesPresenter;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsActivity;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsPresenter;
import hu.herold.mobsoft.recipher.ui.favourites.password.PasswordProtectedActivity;
import hu.herold.mobsoft.recipher.ui.favourites.password.PasswordProtectedPresenter;
import hu.herold.mobsoft.recipher.ui.main.MainActivity;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesFragment;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesPresenter;
import hu.herold.mobsoft.recipher.ui.recipes.details.RecipeDetailsActivity;
import hu.herold.mobsoft.recipher.ui.recipes.details.RecipeDetailsPresenter;

/**
 * Created by herold on 2018. 03. 23..
 */

@Singleton
@Component(modules = {UIModule.class, DbModule.class, InteractorModule.class, NetworkModule.class})
public interface RecipherApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(RecipesFragment recipesFragment);

    void inject(FavouritesFragment favouritesFragment);

    void inject(AboutFragment aboutFragment);

    void inject(RecipesInteractor recipesInteractor);

    void inject(FavouritesInteractor favouritesInteractor);

    void inject(RecipesPresenter recipesPresenter);

    void inject(RecipeDetailsActivity recipeDetailsActivity);

    void inject(RecipeDetailsPresenter recipeDetailsPresenter);

    void inject(FavouritesPresenter favouritesPresenter);

    void inject(FavouriteDetailsPresenter favouriteDetailsPresenter);

    void inject(FavouriteDetailsActivity favouriteDetailsActivity);

    void inject(MockRecipeApi mockRecipeApi);

    void inject(PasswordProtectedPresenter passwordProtectedPresenter);

    void inject(PasswordProtectedActivity passwordProtectedActivity);
}
