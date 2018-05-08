package hu.herold.mobsoft.recipher.network.mock;

import hu.herold.mobsoft.recipher.network.api.RecipeApi;
import hu.herold.mobsoft.recipher.network.model.GetResponse;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.network.model.SearchResponse;
import retrofit2.Call;

/**
 * Created by herold on 2018. 04. 26..
 */

public class MockRecipeApi implements RecipeApi {

    @Override
    public Call<GetResponse> getRecipeById(String rId) {
        return null;
    }

    @Override
    public Call<SearchResponse> searchRecipes(String q, String sort, Integer page) {
        return null;
    }
}
