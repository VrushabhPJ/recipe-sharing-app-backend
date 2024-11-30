package com.zosh.recipe_sharing_youtube.controller;

import com.zosh.recipe_sharing_youtube.model.Recipe;
import com.zosh.recipe_sharing_youtube.model.User;
import com.zosh.recipe_sharing_youtube.service.RecipeService;
import com.zosh.recipe_sharing_youtube.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;
    public RecipeController(RecipeService recipeService1, UserService userService1) {
        this.recipeService = recipeService1;
        this.userService = userService1;
    }

    //@RequestHeader("Authorization") used for accessing the header part of request body.
    @PostMapping("")
    public Recipe createRecipe(@RequestBody Recipe recipe,
                               @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwt(jwt);
        Recipe createdRecipe=  recipeService.createRecipe(recipe, user);
        return createdRecipe;
    }

    @GetMapping()
    public List<Recipe> getAllRecipe() throws Exception {
        List<Recipe> recipes= recipeService.findAllRecipe();
        return recipes;
    }

    @DeleteMapping("/{recipeId}")
    public String deletRecipe(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipeById(recipeId);
        return "recipe deleted successfully";
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe,
                                     @PathVariable Long id) throws Exception {

        Recipe updatedRecipe= recipeService.updateRecipe(recipe, id);
        return updatedRecipe;
    }

    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@RequestHeader("Authorization") String jwt,
                               @PathVariable Long id ) throws Exception {

        User user= userService.findUserByJwt(jwt);
        Recipe updatedRecipe= recipeService.likeRecipe(id , user);
        return updatedRecipe;
    }

}
//3.25.48
