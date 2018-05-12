package hu.herold.mobsoft.recipher.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.db.dao.PasswordDao;
import hu.herold.mobsoft.recipher.db.dao.RecipeDao;
import hu.herold.mobsoft.recipher.db.entity.PasswordEntity;
import hu.herold.mobsoft.recipher.db.repository.RecipeDataSource;
import hu.herold.mobsoft.recipher.db.repository.RecipeRepository;

/**
 * Created by herold on 2018. 04. 24..
 */

@Module
public class DbModule {

    private AppDatabase appDatabase;

    public DbModule(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "app-db").build();
    }

    @Provides
    @Singleton
    AppDatabase provideRoomDatabase() {
        return appDatabase;
    }

    @Provides
    @Singleton
    RecipeDao provideRecipeDao(AppDatabase appDatabase) {
        return appDatabase.recipeDao();
    }

    @Provides
    @Singleton
    PasswordDao providePasswordDao(AppDatabase appDatabase) {
        return appDatabase.passwordDao();
    }

    @Provides
    @Singleton
    RecipeRepository provideRecipeRepository(RecipeDao recipeDao, PasswordDao passwordDao) {
        return new RecipeDataSource(recipeDao, passwordDao);
    }


}
