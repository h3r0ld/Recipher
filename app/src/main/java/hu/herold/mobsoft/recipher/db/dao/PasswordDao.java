package hu.herold.mobsoft.recipher.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import hu.herold.mobsoft.recipher.db.entity.PasswordEntity;
import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;

/**
 * Created by herold on 2018. 05. 12..
 */

@Dao
public interface PasswordDao {

    @Query("SELECT * FROM passwords WHERE :recipeId = recipeId")
    PasswordEntity getPassword(String recipeId);

    @Insert
    void savePassword(PasswordEntity passwordEntity);

    @Delete
    void deletePassword(PasswordEntity passwordEntity);
}
