package hu.herold.mobsoft.recipher.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import java.util.List;

import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;

/**
 * Created by herold on 2018. 04. 24..
 */

@Dao
public interface RecipeDao {

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT recipeId, title, imageUrl, socialRank, isEncrypted FROM RECIPES WHERE title LIKE :query")
    List<RecipeEntity> getRecipes(String query) throws Exception;

    @Query("SELECT recipeId FROM RECIPES")
    List<String> getRecipeIds();

    @Query("SELECT * FROM recipes WHERE :recipeId = recipeId")
    RecipeEntity getRecipeById(String recipeId) throws Exception;

    @Insert
    void saveRecipe(RecipeEntity... recipeEntity);

    @Update
    void updateRecipe(RecipeEntity recipeEntity);

    @Delete
    void deleteRecipe(RecipeEntity recipeEntity);

    @Query("DELETE FROM RECIPES WHERE :id = recipeId")
    void deleteRecipeById(String id);
}
