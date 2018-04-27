package hu.herold.mobsoft.recipher.db.mock;

import java.util.List;

import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;
import hu.herold.mobsoft.recipher.db.repository.RecipeRepository;
import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 04. 27..
 */

public class MockRecipeRepository implements RecipeRepository {
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
