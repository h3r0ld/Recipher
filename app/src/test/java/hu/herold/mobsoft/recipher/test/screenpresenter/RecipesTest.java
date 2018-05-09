package hu.herold.mobsoft.recipher.test.screenpresenter;

import net.bytebuddy.asm.Advice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import hu.herold.mobsoft.recipher.BuildConfig;
import hu.herold.mobsoft.recipher.network.MockRecipeApi;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesPresenter;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesScreen;

import static hu.herold.mobsoft.recipher.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by herold on 2018. 05. 09..
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RecipesTest {

    private RecipesScreen recipesScreen;
    private RecipesPresenter recipesPresenter;
    private String query;

    @Before
    public void setUp() throws Exception {
        setTestInjector();
        recipesScreen = mock(RecipesScreen.class);
        recipesPresenter = new RecipesPresenter();
        recipesPresenter.attachScreen(recipesScreen);
    }

    @Test
    public void testRecipesRefresh() throws Exception {
        query = "Recipe";

        recipesPresenter.refreshRecipes(query);
        ArgumentCaptor<List> recipesCaptor = ArgumentCaptor.forClass(List.class);
        verify(recipesScreen).showRecipes(recipesCaptor.capture());

        assertTrue(recipesCaptor.getValue().size() > 0);
    }

    @Test
    public void testRecipesRefreshNetworkError() throws Exception {
        // This way, the mocked api will throw an exception
        query = MockRecipeApi.THROW_EXCEPTION;

        recipesPresenter.refreshRecipes(query);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(recipesScreen).showNetworkError(messageCaptor.capture());

        assertTrue(!"".equals(messageCaptor.getValue()));
    }

    @After
    public void tearDown() throws Exception {
        recipesPresenter.detachScreen();
    }
}
