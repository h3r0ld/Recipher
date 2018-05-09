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
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesScreen;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsPresenter;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsScreen;

import static hu.herold.mobsoft.recipher.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by herold on 2018. 05. 09..
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class FavouriteDetailsTest {

    private FavouriteDetailsScreen favouriteDetailsScreen;
    private FavouriteDetailsPresenter favouriteDetailsPresenter;
    private Recipe recipe;

    @Before
    public void setUp() throws Exception {
        setTestInjector();
        favouriteDetailsScreen = mock(FavouriteDetailsScreen.class);
        favouriteDetailsPresenter = new FavouriteDetailsPresenter();
        favouriteDetailsPresenter.attachScreen(favouriteDetailsScreen);
    }

    @Test
    public void testGetFavouriteDetails() throws Exception {
        recipe = new Recipe();
        recipe.setRecipeId("1");

        favouriteDetailsPresenter.getRecipeDetails(recipe);

        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(favouriteDetailsScreen).showRecipe(recipeCaptor.capture());

        Recipe recipeWithDetails = recipeCaptor.getValue();

        assertTrue(!"".equals(recipeWithDetails.getPublisher()));
        assertTrue(recipeWithDetails.getIngredients().size() > 0);
    }

    @Test
    public void testGetFavouriteDetailsError() throws Exception {
        recipe = new Recipe();
        recipe.setRecipeId(null);

        favouriteDetailsPresenter.getRecipeDetails(recipe);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(favouriteDetailsScreen).showMessage(messageCaptor.capture());

        assertTrue(!"".equals(messageCaptor.getValue()));
    }

    @Test
    public void testSaveFavouriteRecipe() throws Exception {
        recipe = new Recipe();
        recipe.setRecipeId("1");

        favouriteDetailsPresenter.saveRecipe(recipe);
        verify(favouriteDetailsScreen).savedRecipe();
    }

    @Test
    public void testDeleteFavouriteRecipe() throws Exception {
        favouriteDetailsPresenter.deleteRecipe("1");
        verify(favouriteDetailsScreen).deletedRecipe();
    }

    @After
    public void tearDown() throws Exception {
        favouriteDetailsPresenter.detachScreen();
    }
}
