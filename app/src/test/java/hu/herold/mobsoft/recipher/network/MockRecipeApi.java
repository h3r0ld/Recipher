package hu.herold.mobsoft.recipher.network;

import java.io.IOException;
import java.util.Arrays;

import hu.herold.mobsoft.recipher.network.api.RecipeApi;
import hu.herold.mobsoft.recipher.network.model.GetResponse;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.network.model.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by herold on 2018. 04. 26..
 */

public class MockRecipeApi implements RecipeApi {

    public static final String THROW_EXCEPTION = "THROW_EXCEPTION";

    @Override
    public Call<GetResponse> getRecipeById(String rId) throws Exception {

        if (rId == null) {
            throw new Exception("NetworkException");
        }

        final Recipe recipe = createRecipe("recipeId", "Recipe", "image.com/img1", "Publisher", 100.0, "1 potato", "2 tomatoes", "3 kg meat", "salt");
        final GetResponse getResponse = new GetResponse();
        getResponse.setRecipe(recipe);

        Call<GetResponse> call = new Call<GetResponse>() {
            @Override
            public Response<GetResponse> execute() throws IOException {
                return Response.success(getResponse);
            }

            @Override
            public void enqueue(Callback<GetResponse> callback) {}

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<GetResponse> clone() {
                return null;
            }
        };

        return call;
    }

    @Override
    public Call<SearchResponse> searchRecipes(String q, String sort, Integer page) throws Exception {

        if (THROW_EXCEPTION.equals(q)) {
            throw new Exception("NetworkException");
        }

        final SearchResponse searchResponse = new SearchResponse();

        searchResponse.setRecipes(Arrays.asList(
            createRecipe("1", "Recipe1", "image.com/img1", "Publisher1", 100.0, "1 potato", "2 tomatoes", "3 kg meat", "salt"),
            createRecipe("2", "Recipe2", "image.com/img2", "Publisher2", 99.0, "2 potato", "3 tomatoes", "4 kg meat", "salt"),
            createRecipe("3", "Recipe3", "image.com/img3", "Publisher3", 98.0, "3 potato", "4 tomatoes", "5 kg meat", "salt")
        ));

        Call<SearchResponse> call = new Call<SearchResponse>() {
            @Override
            public Response<SearchResponse> execute() throws IOException {
                return Response.success(searchResponse);
            }

            @Override
            public void enqueue(Callback<SearchResponse> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<SearchResponse> clone() {
                return null;
            }
        };

        return call;
    }

    private Recipe createRecipe(String recipeId, String title, String imageUrl, String publisher, Double socialRank, String... ingredients) {
        Recipe recipe = new Recipe();

        recipe.setRecipeId(recipeId);
        recipe.setTitle(title);
        recipe.setImageUrl(imageUrl);
        recipe.setPublisher(publisher);
        recipe.setSocialRank(socialRank);
        recipe.setIngredients(Arrays.asList(ingredients));

        return recipe;
    }
}
