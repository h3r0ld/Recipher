package hu.herold.mobsoft.recipher.test.screenpresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import hu.herold.mobsoft.recipher.BuildConfig;
import hu.herold.mobsoft.recipher.db.MockRecipeDao;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesPresenter;
import hu.herold.mobsoft.recipher.ui.favourites.FavouritesScreen;

import static hu.herold.mobsoft.recipher.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by herold on 2018. 05. 09..
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class FavouritesTest {

    private FavouritesScreen favouritesScreen;
    private FavouritesPresenter favouritesPresenter;
    private String query;

    @Before
    public void setUp() throws Exception {
        setTestInjector();
        favouritesScreen = mock(FavouritesScreen.class);
        favouritesPresenter = new FavouritesPresenter();
        favouritesPresenter.attachScreen(favouritesScreen);
    }

    @Test
    public void testFavouritesRefresh() throws Exception {
        query = "Favourite";
        favouritesPresenter.refreshFavouriteRecipes(query, new ArrayList<String>());

        ArgumentCaptor<List> favouritesCaptor = ArgumentCaptor.forClass(List.class);
        verify(favouritesScreen).showFavouriteRecipes(favouritesCaptor.capture());
        assertTrue(favouritesCaptor.getValue().size() > 0);
    }

    @Test
    public void testFavouritesRefreshError() throws Exception {
        query = MockRecipeDao.THROW_EXCEPTION;

        favouritesPresenter.refreshFavouriteRecipes(query, new ArrayList<String>());

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(favouritesScreen).showMessage(messageCaptor.capture());
        assertTrue(!"".equals(messageCaptor.getValue()));
    }

    @After
    public void tearDown() throws Exception {
        favouritesPresenter.detachScreen();
    }
}
