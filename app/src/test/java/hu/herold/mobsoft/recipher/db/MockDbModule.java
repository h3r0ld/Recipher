package hu.herold.mobsoft.recipher.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.db.dao.RecipeDao;
import hu.herold.mobsoft.recipher.db.repository.RecipeDataSource;
import hu.herold.mobsoft.recipher.db.repository.RecipeRepository;

/**
 * Created by herold on 2018. 04. 27..
 */

@Module
public class MockDbModule {

    private AppDatabase appDatabase;

    public MockDbModule(Context context) {}

    @Provides
    @Singleton
    AppDatabase provideRoomDatabase() {
        return new AppDatabase() {
            @Override
            public RecipeDao recipeDao() {
                return null;
            }

            @Override
            protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
                return null;
            }

            @Override
            protected InvalidationTracker createInvalidationTracker() {
                return null;
            }
        };
    }

    @Provides
    @Singleton
    RecipeDao provideRecipeDao(AppDatabase appDatabase) {
        return new MockRecipeDao();
    }

    @Provides
    @Singleton
    RecipeRepository provideRecipeRepository(RecipeDao recipeDao) {
        return new RecipeDataSource(recipeDao);
    }
}
