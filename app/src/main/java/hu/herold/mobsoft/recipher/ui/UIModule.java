package hu.herold.mobsoft.recipher.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.ui.about.AboutPresenter;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesPresenter;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsPresenter;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsScreen;
import hu.herold.mobsoft.recipher.ui.main.MainPresenter;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesPresenter;
import hu.herold.mobsoft.recipher.ui.recipes.details.RecipeDetailsPresenter;

/**
 * Created by herold on 2018. 03. 23..
 */

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public RecipesPresenter provideRecipesPresenter() {
        return new RecipesPresenter();
    }

    @Provides
    @Singleton
    public RecipeDetailsPresenter provideRecipeDetailsPresenter() {
        return new RecipeDetailsPresenter();
    }

    @Provides
    @Singleton
    public FavouritesPresenter provideFavouritesPresenter() {
        return new FavouritesPresenter();
    }

    @Provides
    @Singleton
    public AboutPresenter provideAboutPresenter()
    {
        return new AboutPresenter();
    }

    @Provides
    @Singleton
    public FavouriteDetailsPresenter provideFavouriteDetailsPresenter() {
        return new FavouriteDetailsPresenter();
    }
}
