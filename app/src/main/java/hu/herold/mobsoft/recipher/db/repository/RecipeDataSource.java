package hu.herold.mobsoft.recipher.db.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.db.dao.PasswordDao;
import hu.herold.mobsoft.recipher.db.dao.RecipeDao;
import hu.herold.mobsoft.recipher.db.entity.PasswordEntity;
import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;
import hu.herold.mobsoft.recipher.db.mapper.Mapper;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.utils.password.PasswordService;

/**
 * Created by herold on 2018. 04. 24..
 */

public class RecipeDataSource implements RecipeRepository {

    private final RecipeDao recipeDao;
    private final PasswordDao passwordDao;

    @Inject
    public RecipeDataSource(RecipeDao recipeDao, PasswordDao passwordDao) {
        this.recipeDao = recipeDao;
        this.passwordDao = passwordDao;
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
    public Map<String, Boolean> getRecipeIds() {
        Map<String, Boolean> recipeIds = new HashMap<>();

        List<RecipeEntity> recipeEntities = recipeDao.getRecipeIds();

        for (RecipeEntity recipeEntity: recipeEntities) {
            recipeIds.put(recipeEntity.getRecipeId(), recipeEntity.isProtected());
        }

        return recipeIds;
    }

    @Override
    public void saveRecipe(Recipe recipe, String password) throws Exception {
        RecipeEntity recipeEntity = getRecipeById(recipe.getRecipeId());

        if (recipeEntity != null) {
            recipeEntity = Mapper.mapRecipeEntity(recipe);
            recipeDao.updateRecipe(recipeEntity);
        } else {
            recipeEntity = Mapper.mapRecipeEntity(recipe);
            recipeDao.saveRecipe(recipeEntity);
        }

        if (recipeEntity.isProtected() && !"".equals(password)) {
            PasswordService passwordService = new PasswordService();

            PasswordEntity passwordEntity = passwordService.hashPassword(password);
            passwordEntity.setRecipeId(recipe.getRecipeId());

            passwordDao.savePassword(passwordEntity);
        }
    }

    @Override
    public void deleteRecipe(RecipeEntity recipeEntity) {
        recipeDao.deleteRecipe(recipeEntity);

        if (recipeEntity.isProtected()) {
            deletePassword(recipeEntity.getRecipeId());
        };
    }

    @Override
    public void deleteRecipeById(String id) {
        recipeDao.deleteRecipeById(id);
        deletePassword(id);
    }

    @Override
    public PasswordEntity getPasswordForRecipe(String recipeId) {
        return passwordDao.getPassword(recipeId);
    }

    @Override
    public void savePasswordForRecipe(PasswordEntity passwordEntity) {
        passwordDao.savePassword(passwordEntity);
    }

    private void deletePassword(String recipeId) {
        PasswordEntity passwordEntity = passwordDao.getPassword(recipeId);

        if (passwordEntity != null) {
            passwordDao.deletePassword(passwordEntity);
        }
    }
}
