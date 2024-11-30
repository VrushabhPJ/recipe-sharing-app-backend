package com.zosh.recipe_sharing_youtube.repository;

import com.zosh.recipe_sharing_youtube.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe , Long> {

}
