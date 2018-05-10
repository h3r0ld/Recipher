package hu.herold.mobsoft.recipher;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import org.apache.tools.ant.taskdefs.Exec;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.ui.UIModule;
import hu.herold.mobsoft.recipher.ui.about.AboutPresenter;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesPresenter;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsPresenter;
import hu.herold.mobsoft.recipher.ui.main.MainPresenter;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesPresenter;
import hu.herold.mobsoft.recipher.ui.recipes.details.RecipeDetailsPresenter;
import hu.herold.mobsoft.recipher.utils.UIExecutor;

import static org.mockito.Mockito.mock;

/**
 * Created by herold on 2018. 05. 09..
 */

@Module
public class TestModule {

    private Context context;

    public TestModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public GoogleAnalytics provideGoogleAnalytics(Context context) {
        return mock(GoogleAnalytics.class);
    }

    @Provides
    @Singleton
    public Tracker provideTracker(GoogleAnalytics googleAnalytics) {
        return mock(Tracker.class);
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
    public AboutPresenter provideAboutPresenter() {
        return new AboutPresenter();
    }

    @Provides
    @Singleton
    public FavouriteDetailsPresenter provideFavouriteDetailsPresenter() {
        return new FavouriteDetailsPresenter();
    }

    @Provides
    @Singleton
    public Executor provideExecutor() {
        return new UIExecutor();
    }
}
