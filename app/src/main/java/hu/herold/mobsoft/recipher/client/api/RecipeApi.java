package hu.herold.mobsoft.recipher.client.api;

import hu.herold.mobsoft.recipher.client.model.Recipe;
import hu.herold.mobsoft.recipher.client.model.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {
  
  /**
   * Get a recipe by id.
   * 
   * @param rId Id of desired recipe as returned by Search             Query
   * @return Call<Recipe>
   */
  
  @GET("get")
  Call<Recipe> getRecipeById(
    @Query("rId") String rId
  );

  
  /**
   * Search recipes.
   * 
   * @param q Search Query (Ingredients should be                    separated by commas). If this is omitted               top rated recipes will be returned
   * @param sort How the results should be sorted. \&quot;r\&quot; - by rating, \&quot;t\&quot; - by trendingness.
   * @param page Used to get additional results.
   * @return Call<SearchResponse>
   */
  
  @GET("search")
  Call<SearchResponse> searchRecipes(
    @Query("q") String q, @Query("sort") String sort, @Query("page") Integer page
  );

  
}
