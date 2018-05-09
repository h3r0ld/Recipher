package hu.herold.mobsoft.recipher.test.screenpresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import hu.herold.mobsoft.recipher.BuildConfig;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.ui.recipes.details.RecipeDetailsPresenter;
import hu.herold.mobsoft.recipher.ui.recipes.details.RecipeDetailsScreen;

import static hu.herold.mobsoft.recipher.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by herold on 2018. 05. 09..
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RecipeDetailsTest {

    private RecipeDetailsScreen recipeDetailsScreen;
    private RecipeDetailsPresenter recipeDetailsPresenter;
    private Recipe recipe;

    @Before
    public void setUp() throws Exception {
        setTestInjector();
        recipeDetailsScreen = mock(RecipeDetailsScreen.class);
        recipeDetailsPresenter = new RecipeDetailsPresenter();
        recipeDetailsPresenter.attachScreen(recipeDetailsScreen);
    }

    @Test
    public void testGetRecipeDetails() throws Exception {
        recipe = new Recipe();
        recipe.setRecipeId("1");

        recipeDetailsPresenter.getRecipeDetails(recipe);

        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeDetailsScreen).showRecipe(recipeCaptor.capture());

        Recipe recipeWithDetails = recipeCaptor.getValue();

        assertTrue(!"".equals(recipeWithDetails.getPublisher()));
        assertTrue(recipeWithDetails.getIngredients().size() > 0);
    }

    @Test
    public void testGetRecipeDetailsError() throws Exception {
        recipe = new Recipe();
        recipe.setRecipeId(null);

        recipeDetailsPresenter.getRecipeDetails(recipe);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(recipeDetailsScreen).showNetworkError(messageCaptor.capture());

        assertTrue(!"".equals(messageCaptor.getValue()));
    }

    @Test
    public void testSaveFavouriteRecipe() throws Exception {
        recipe = new Recipe();
        recipe.setRecipeId("1");

        recipeDetailsPresenter.saveFavouriteRecipe(recipe);
        verify(recipeDetailsScreen).savedRecipe();
    }

    @Test
    public void testDeleteFavouriteRecipe() throws Exception {
        recipeDetailsPresenter.deleteFavouriteRecipe("1");
        verify(recipeDetailsScreen).deletedRecipe();
    }

    @After
    public void tearDown() throws Exception {
        recipeDetailsPresenter.detachScreen();
    }
}
