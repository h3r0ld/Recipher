package hu.herold.mobsoft.recipher.network.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class SearchResponse   {
  
  @SerializedName("count")
  private Long count = null;
  
  @SerializedName("recipes")
  private List<Recipe> recipes = new ArrayList<Recipe>();
  

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Long getCount() {
    return count;
  }
  public void setCount(Long count) {
    this.count = count;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Recipe> getRecipes() {
    return recipes;
  }
  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchResponse searchResponse = (SearchResponse) o;
    return Objects.equals(count, searchResponse.count) &&
        Objects.equals(recipes, searchResponse.recipes);
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  @Override
  public int hashCode() {
    return Objects.hash(count, recipes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchResponse {\n");
    
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    recipes: ").append(toIndentedString(recipes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
