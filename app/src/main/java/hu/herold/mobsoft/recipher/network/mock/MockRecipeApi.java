package hu.herold.mobsoft.recipher.network.mock;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.network.api.RecipeApi;
import hu.herold.mobsoft.recipher.network.model.GetResponse;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.network.model.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by herold on 2018. 05. 10..
 */

public class MockRecipeApi implements RecipeApi {

    @Inject
    Context context;

    private List<Recipe> recipes;

    public MockRecipeApi() {
        RecipherApplication.injector.inject(this);

        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Call<GetResponse> getRecipeById(String rId) throws Exception {

        final GetResponse getResponse = new GetResponse();

        for (Recipe recipe : recipes) {
            if (recipe.getRecipeId().equals(rId)) {
                getResponse.setRecipe(recipe);
                break;
            }
        }

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
        final SearchResponse searchResponse = new SearchResponse();

        List<Recipe> searchedRecipes = new ArrayList<>();

        for (Recipe recipe: recipes) {
            if (recipe.getTitle().contains(q)) {
                searchedRecipes.add(recipe);
            }
        }

        searchResponse.setRecipes(searchedRecipes);

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

    private void initData() throws IOException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.mock_data);

        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            SearchResponse searchResponse = new Gson().fromJson(reader, SearchResponse.class);
            recipes = searchResponse.getRecipes();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }
}
