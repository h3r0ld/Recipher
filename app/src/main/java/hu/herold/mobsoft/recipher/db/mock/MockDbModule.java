package hu.herold.mobsoft.recipher.db.mock;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.db.repository.RecipeDataSource;
import hu.herold.mobsoft.recipher.db.repository.RecipeRepository;

/**
 * Created by herold on 2018. 04. 27..
 */

@Module
public class MockDbModule {

    @Provides
    @Singleton
    RecipeRepository provideRecipeRepository() {
        return new MockRecipeRepository();
    }
}
