package hu.herold.mobsoft.recipher.db.repository;

import java.util.List;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.db.dao.RecipeDao;
import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;
import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 04. 24..
 */

public class RecipeDataSource implements RecipeRepository {

    private final RecipeDao recipeDao;

    @Inject
    public RecipeDataSource(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Override
    public List<RecipeEntity> getRecipes(String filter) {
        return null;
    }

    @Override
    public RecipeEntity getRecipeById(String id) {
        return null;
    }

    @Override
    public void addNewRecipe(Recipe recipe, boolean isEncrypted) {

    }

    @Override
    public void updateRecipe(RecipeEntity recipeEntity) {

    }

    @Override
    public void deleteRecipe(RecipeEntity recipeEntity) {

    }
}
