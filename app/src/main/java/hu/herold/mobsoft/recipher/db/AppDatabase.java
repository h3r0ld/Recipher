package hu.herold.mobsoft.recipher.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import hu.herold.mobsoft.recipher.db.converters.Converters;
import hu.herold.mobsoft.recipher.db.dao.RecipeDao;
import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;

/**
 * Created by herold on 2018. 04. 24..
 */

@Database(entities = {RecipeEntity.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();
}
