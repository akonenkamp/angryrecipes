package com.libertymutual.goforcode.angryrecipe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.angryrecipe.models.Recipe;

@Repository 
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

    List<Recipe> findByTitleContainingIgnoreCase(String partOfName);
}
