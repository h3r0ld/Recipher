package hu.herold.mobsoft.recipher.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by herold on 2018. 04. 04..
 */

@Entity(tableName = "recipes")
public class RecipeEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recipeId")
    private String recipeId = null;

    @ColumnInfo(name = "description")
    private String description = null;

    @ColumnInfo(name = "f2fUrl")
    private String f2fUrl;

    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @ColumnInfo(name = "ingredients")
    private List<String> ingredients;

    @ColumnInfo(name = "publisher")
    private String publisher;

    @ColumnInfo(name = "publisherUrl")
    private String publisherUrl;

    @ColumnInfo(name = "socialRank")
    private Double socialRank;

    @ColumnInfo(name = "sourceUrl")
    private String sourceUrl;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "isProtected")
    private boolean isProtected;


    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getF2fUrl() {
        return f2fUrl;
    }

    public void setF2fUrl(String f2fUrl) {
        this.f2fUrl = f2fUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    public void setPublisherUrl(String publisherUrl) {
        this.publisherUrl = publisherUrl;
    }

    public Double getSocialRank() {
        return socialRank;
    }

    public void setSocialRank(Double socialRank) {
        this.socialRank = socialRank;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }
}
