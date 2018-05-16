package hu.herold.mobsoft.recipher.db.mapper;

import hu.herold.mobsoft.recipher.db.entity.RecipeEntity;
import hu.herold.mobsoft.recipher.network.model.Recipe;

/**
 * Created by herold on 2018. 05. 08..
 */

public class Mapper {

    public static RecipeEntity mapRecipeEntity(Recipe recipe) {
        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setRecipeId(recipe.getRecipeId());
        recipeEntity.setDescription(recipe.getDescription());
        recipeEntity.setF2fUrl(recipe.getF2fUrl());
        recipeEntity.setImageUrl(recipe.getImageUrl());
        recipeEntity.setIngredients(recipe.getIngredients());
        recipeEntity.setPublisher(recipe.getPublisher());
        recipeEntity.setPublisherUrl(recipe.getPublisherUrl());
        recipeEntity.setSocialRank(recipe.getSocialRank());
        recipeEntity.setSourceUrl(recipe.getSourceUrl());
        recipeEntity.setTitle(recipe.getTitle());
        recipeEntity.setProtected(recipe.getIsProtected() == null ? false : recipe.getIsProtected());

        return recipeEntity;
    }

    public static Recipe mapRecipe(RecipeEntity recipeEntity) {
        Recipe recipe = new Recipe();

        recipe.setRecipeId(recipeEntity.getRecipeId());
        recipe.setDescription(recipeEntity.getDescription());
        recipe.setF2fUrl(recipeEntity.getF2fUrl());
        recipe.setImageUrl(recipeEntity.getImageUrl());
        recipe.setIngredients(recipeEntity.getIngredients());
        recipe.setPublisher(recipeEntity.getPublisher());
        recipe.setPublisherUrl(recipeEntity.getPublisherUrl());
        recipe.setSocialRank(recipeEntity.getSocialRank());
        recipe.setSourceUrl(recipeEntity.getSourceUrl());
        recipe.setTitle(recipeEntity.getTitle());
        recipe.setIsProtected(recipeEntity.isProtected());
        recipe.setFavourite(true);

        return recipe;
    }
}
