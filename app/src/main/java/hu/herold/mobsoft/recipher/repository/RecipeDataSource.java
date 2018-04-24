package hu.herold.mobsoft.recipher.repository;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.db.dao.RecipeDao;

/**
 * Created by herold on 2018. 04. 24..
 */

public class RecipeDataSource implements RecipeRepository {

    @Inject
    RecipeDao recipeDao;


    public RecipeDataSource() {
        // Required empty public constructor
        RecipherApplication.injector.inject(this);
    }

}
