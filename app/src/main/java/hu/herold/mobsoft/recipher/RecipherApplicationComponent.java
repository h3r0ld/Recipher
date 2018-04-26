package hu.herold.mobsoft.recipher;

import javax.inject.Singleton;

import dagger.Component;
import hu.herold.mobsoft.recipher.db.DbModule;
import hu.herold.mobsoft.recipher.interactor.InteractorModule;
import hu.herold.mobsoft.recipher.interactor.favourites.FavouritesInteractor;
import hu.herold.mobsoft.recipher.interactor.recipes.RecipesInteractor;
import hu.herold.mobsoft.recipher.network.NetworkModule;
import hu.herold.mobsoft.recipher.db.repository.RecipeDataSource;
import hu.herold.mobsoft.recipher.ui.UIModule;
import hu.herold.mobsoft.recipher.ui.about.AboutFragment;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesFragment;
import hu.herold.mobsoft.recipher.ui.main.MainActivity;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesFragment;

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
}
