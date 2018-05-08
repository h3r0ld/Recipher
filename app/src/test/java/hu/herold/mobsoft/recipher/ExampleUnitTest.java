package hu.herold.mobsoft.recipher;

import org.junit.Ignore;
import org.junit.Test;

import hu.herold.mobsoft.recipher.network.ApiClient;
import hu.herold.mobsoft.recipher.network.api.RecipeApi;
import hu.herold.mobsoft.recipher.network.model.GetResponse;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.network.model.SearchResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Ignore
    @Test
    public void addition_isCorrect() throws Exception {
        ApiClient apiClient = new ApiClient(System.getenv("FOOD2FORK_API_KEY"));

        RecipeApi api = apiClient.createService(RecipeApi.class);

        Call<SearchResponse> searchResponseCall = api.searchRecipes("", null, null);

        Response<SearchResponse> searchResponse = searchResponseCall.execute();

        System.out.println("SearchResponse: " + searchResponse);

        if (searchResponse.code() != 200) {
            System.out.println("Code:" + searchResponse.code());
            throw new Exception("Result code is not 200");
        }
        else {

        }


        System.out.println("Count: " + searchResponse.body().getCount());
        System.out.println("Recipes: " + searchResponse.body().getRecipes());
    }


}