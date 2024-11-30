package com.zosh.recipe_sharing_youtube.service;

import com.zosh.recipe_sharing_youtube.model.Recipe;
import com.zosh.recipe_sharing_youtube.model.User;

import java.util.List;

public interface RecipeService {

    public Recipe createRecipe(Recipe recipe, User user);

    public Recipe findRecipeById(Long id) throws Exception;

    public void deleteRecipeById(Long id) throws Exception;

    public Recipe updateRecipe(Recipe recipe, Long id) throws  Exception;

    public List<Recipe> findAllRecipe();

    public Recipe likeRecipe(Long recipeId , User user) throws Exception;



}
