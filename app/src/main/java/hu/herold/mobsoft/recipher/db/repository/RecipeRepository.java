package hu.herold.mobsoft.recipher.db.repository;

import java.util.List;

import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;
import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 04. 24..
 */

public interface RecipeRepository {

    List<RecipeEntity> getRecipes(String filter) throws Exception;

    List<String> getRecipeIds();

    RecipeEntity getRecipeById(String id) throws Exception;

    void saveRecipe(Recipe recipe) throws Exception;

    void deleteRecipe(RecipeEntity recipeEntity);

    void deleteRecipeById(String id);
}
