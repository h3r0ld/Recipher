package hu.herold.mobsoft.recipher.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import java.util.List;

import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;

/**
 * Created by herold on 2018. 04. 24..
 */

@Dao
public interface RecipeDao {

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT recipeId, title, imageUrl, socialRank, isEncrypted FROM recipes")
    List<RecipeEntity> getRecipes();

    @Query("SELECT * FROM recipes WHERE :recipeId = recipeId")
    List<RecipeEntity> getRecipeById(String recipeId);

    @Insert
    void saveRecipe(RecipeEntity recipeEntity);

    @Delete
    void deleteRecipe(RecipeEntity recipeEntity);
}
