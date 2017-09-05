package com.libertymutual.goforcode.angryrecipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libertymutual.goforcode.angryrecipe.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
