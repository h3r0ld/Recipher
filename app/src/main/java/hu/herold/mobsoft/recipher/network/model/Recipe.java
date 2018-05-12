package hu.herold.mobsoft.recipher.network.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;




/**
 * Full recipe.
 **/
@ApiModel(description = "Full recipe.")
public class Recipe   {
  
  @SerializedName("recipe_id")
  private String recipeId = null;
  
  @SerializedName("description")
  private String description = null;
  
  @SerializedName("f2f_url")
  private String f2fUrl = null;
  
  @SerializedName("image_url")
  private String imageUrl = null;
  
  @SerializedName("ingredients")
  private List<String> ingredients = new ArrayList<String>();
  
  @SerializedName("publisher")
  private String publisher = null;
  
  @SerializedName("publisher_url")
  private String publisherUrl = null;
  
  @SerializedName("social_rank")
  private Double socialRank = null;
  
  @SerializedName("source_url")
  private String sourceUrl = null;
  
  @SerializedName("title")
  private String title = null;
  
  @SerializedName("favourite")
  private Boolean favourite = null;

  @SerializedName("isProtected")
  private Boolean isProtected = null;
  

  
  /**
   * The ID of the recipe.
   **/
  @ApiModelProperty(required = true, value = "The ID of the recipe.")
  public String getRecipeId() {
    return recipeId;
  }
  public void setRecipeId(String recipeId) {
    this.recipeId = recipeId;
  }

  
  /**
   * The description of the recipe.
   **/
  @ApiModelProperty(value = "The description of the recipe.")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   * Url of the recipe on Food2Fork.com
   **/
  @ApiModelProperty(value = "Url of the recipe on Food2Fork.com")
  public String getF2fUrl() {
    return f2fUrl;
  }
  public void setF2fUrl(String f2fUrl) {
    this.f2fUrl = f2fUrl;
  }

  
  /**
   * URL of the image
   **/
  @ApiModelProperty(value = "URL of the image")
  public String getImageUrl() {
    return imageUrl;
  }
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  
  /**
   * The ingredients of this recipe
   **/
  @ApiModelProperty(value = "The ingredients of this recipe")
  public List<String> getIngredients() {
    return ingredients;
  }
  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients;
  }

  
  /**
   * Name of the Publisher
   **/
  @ApiModelProperty(value = "Name of the Publisher")
  public String getPublisher() {
    return publisher;
  }
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  
  /**
   * Base url of the publisher
   **/
  @ApiModelProperty(value = "Base url of the publisher")
  public String getPublisherUrl() {
    return publisherUrl;
  }
  public void setPublisherUrl(String publisherUrl) {
    this.publisherUrl = publisherUrl;
  }

  
  /**
   * The Social Ranking of the Recipe (As determined by the F2F Ranking Algorithm)
   **/
  @ApiModelProperty(value = "The Social Ranking of the Recipe (As determined by the F2F Ranking Algorithm)")
  public Double getSocialRank() {
    return socialRank;
  }
  public void setSocialRank(Double socialRank) {
    this.socialRank = socialRank;
  }

  
  /**
   * Original Url of the recipe on the publisher's site
   **/
  @ApiModelProperty(value = "Original Url of the recipe on the publisher's site")
  public String getSourceUrl() {
    return sourceUrl;
  }
  public void setSourceUrl(String sourceUrl) {
    this.sourceUrl = sourceUrl;
  }

  
  /**
   * Title of the recipe
   **/
  @ApiModelProperty(value = "Title of the recipe")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  
  /**
   * Indicates whether this recipe was favourited or not.
   **/
  @ApiModelProperty(value = "Indicates whether this recipe was favourited or not.")
  public Boolean getFavourite() {
    return favourite;
  }
  public void setFavourite(Boolean favourite) {
    this.favourite = favourite;
  }

  @ApiModelProperty(value = "Indicates whether this recipe is protected with a password, or not.")
  public Boolean getIsProtected() {
    return isProtected;
  }
  public void setIsProtected(Boolean isProtected) {
    this.isProtected= isProtected;
  }
  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Recipe recipe = (Recipe) o;
    return Objects.equals(recipeId, recipe.recipeId) &&
        Objects.equals(description, recipe.description) &&
        Objects.equals(f2fUrl, recipe.f2fUrl) &&
        Objects.equals(imageUrl, recipe.imageUrl) &&
        Objects.equals(ingredients, recipe.ingredients) &&
        Objects.equals(publisher, recipe.publisher) &&
        Objects.equals(publisherUrl, recipe.publisherUrl) &&
        Objects.equals(socialRank, recipe.socialRank) &&
        Objects.equals(sourceUrl, recipe.sourceUrl) &&
        Objects.equals(title, recipe.title) &&
        Objects.equals(favourite, recipe.favourite) &&
            Objects.equals(isProtected, recipe.isProtected);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipeId, description, f2fUrl, imageUrl, ingredients, publisher, publisherUrl, socialRank, sourceUrl, title, favourite, isProtected);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Recipe {\n");
    
    sb.append("    recipeId: ").append(toIndentedString(recipeId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    f2fUrl: ").append(toIndentedString(f2fUrl)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    ingredients: ").append(toIndentedString(ingredients)).append("\n");
    sb.append("    publisher: ").append(toIndentedString(publisher)).append("\n");
    sb.append("    publisherUrl: ").append(toIndentedString(publisherUrl)).append("\n");
    sb.append("    socialRank: ").append(toIndentedString(socialRank)).append("\n");
    sb.append("    sourceUrl: ").append(toIndentedString(sourceUrl)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    favourite: ").append(toIndentedString(favourite)).append("\n");
    sb.append("    isProtected: ").append(toIndentedString(isProtected)).append("\n");
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
