package hu.herold.mobsoft.recipher.db;

import java.util.Arrays;
import java.util.List;

import hu.herold.mobsoft.recipher.db.dao.RecipeDao;
import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;
import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 05. 09..
 */

public class MockRecipeDao implements RecipeDao {

    public static final String THROW_EXCEPTION = "THROW_EXCEPTION";

    @Override
    public List<RecipeEntity> getRecipes(String query) throws Exception {
        if (query.contains(THROW_EXCEPTION)) {
            throw new Exception("DaoException");
        }

        List<RecipeEntity> recipeEntities = Arrays.asList(
            createRecipe("1", "Favourite1", "image.com/img1", "Publisher1", 100.0, "1 potato", "2 tomatoes", "3 kg meat", "salt"),
            createRecipe("2", "Favourite2", "image.com/img2", "Publisher2", 99.0, "2 potato", "3 tomatoes", "4 kg meat", "salt"),
            createRecipe("3", "Favourite3", "image.com/img3", "Publisher3", 98.0, "3 potato", "4 tomatoes", "5 kg meat", "salt")
        );

        return recipeEntities;
    }

    @Override
    public List<String> getRecipeIds() {
        return Arrays.asList("1", "2", "3");
    }

    @Override
    public RecipeEntity getRecipeById(String recipeId) throws Exception {
        if (recipeId == null) {
            throw new Exception("DaoException");
        }

        return createRecipe("1", "Recipe1", "image.com/img1", "Publisher1", 100.0, "1 potato", "2 tomatoes", "3 kg meat", "salt");
    }

    @Override
    public void saveRecipe(RecipeEntity... recipeEntity) { }

    @Override
    public void updateRecipe(RecipeEntity recipeEntity) { }

    @Override
    public void deleteRecipe(RecipeEntity recipeEntity) { }

    @Override
    public void deleteRecipeById(String id) { }

    private RecipeEntity createRecipe(String recipeId, String title, String imageUrl, String publisher, Double socialRank, String... ingredients) {
        RecipeEntity recipe = new RecipeEntity();

        recipe.setRecipeId(recipeId);
        recipe.setTitle(title);
        recipe.setImageUrl(imageUrl);
        recipe.setPublisher(publisher);
        recipe.setSocialRank(socialRank);
        recipe.setIngredients(Arrays.asList(ingredients));

        return recipe;
    }
}
