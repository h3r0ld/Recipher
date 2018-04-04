package hu.herold.mobsoft.recipher.ui.favourites;

import java.util.List;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.interactor.favourites.FavouritesInteractor;
import hu.herold.mobsoft.recipher.ui.Presenter;

/**
 * Created by herold on 2018. 03. 23..
 */

public class FavouritesPresenter extends Presenter<FavouritesScreen> {

    @Inject
    FavouritesInteractor favouritesInteractor;

    public void refreshFavouriteRecipes(String title, List<String> ingredients) {

    }
}
