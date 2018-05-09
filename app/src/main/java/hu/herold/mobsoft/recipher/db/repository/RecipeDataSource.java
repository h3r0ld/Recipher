package hu.herold.mobsoft.recipher.db.repository;

import java.util.List;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.db.dao.RecipeDao;
import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;
import hu.herold.mobsoft.recipher.db.mapper.Mapper;
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
    public List<RecipeEntity> getRecipes(String filter) throws Exception {
        filter = "%" + filter + "%";
        return recipeDao.getRecipes(filter);
    }

    @Override
    public RecipeEntity getRecipeById(String id) throws Exception {
        return recipeDao.getRecipeById(id);
    }

    @Override
    public List<String> getRecipeIds() {
        return recipeDao.getRecipeIds();
    }

    @Override
    public void saveRecipe(Recipe recipe) throws Exception {
        RecipeEntity recipeEntity = getRecipeById(recipe.getRecipeId());

        if (recipeEntity != null) {
            recipeEntity = Mapper.mapRecipeEntity(recipe);
            recipeDao.updateRecipe(recipeEntity);
        } else {
            recipeEntity = Mapper.mapRecipeEntity(recipe);
            recipeDao.saveRecipe(recipeEntity);
        }
    }

    @Override
    public void deleteRecipe(RecipeEntity recipeEntity) {
        recipeDao.deleteRecipe(recipeEntity);
    }

    @Override
    public void deleteRecipeById(String id) {
        recipeDao.deleteRecipeById(id);
    }
}
