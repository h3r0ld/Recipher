package hu.herold.mobsoft.recipher.network.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class GetResponse   {
  
  @SerializedName("recipe")
  private Recipe recipe = null;
  

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Recipe getRecipe() {
    return recipe;
  }
  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetResponse getResponse = (GetResponse) o;
    return Objects.equals(recipe, getResponse.recipe);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipe);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetResponse {\n");
    
    sb.append("    recipe: ").append(toIndentedString(recipe)).append("\n");
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
