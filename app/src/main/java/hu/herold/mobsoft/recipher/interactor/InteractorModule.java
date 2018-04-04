package hu.herold.mobsoft.recipher.interactor;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.interactor.favourites.FavouritesInteractor;
import hu.herold.mobsoft.recipher.interactor.recipes.RecipesInteractor;

/**
 * Created by herold on 2018. 03. 23..
 */

@Module
public class InteractorModule {

    @Provides
    public RecipesInteractor provideRecipesInteractor() {
        return new RecipesInteractor();
    }

    @Provides
    public FavouritesInteractor provideFavouritesInteractor() {
        return new FavouritesInteractor();
    }
}
