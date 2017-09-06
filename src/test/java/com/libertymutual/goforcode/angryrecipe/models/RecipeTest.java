package com.libertymutual.goforcode.angryrecipe.models;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class RecipeTest {
	
	private Recipe recipe;
	
	@Before 
	public void setUp() {
		recipe = new Recipe();
	}
	
	@Test
	public void test_add_recipe() {
		//arrange 
		Ingredient ingredient = new Ingredient();
		
		//act
		recipe.addIngredient(ingredient);
		
		//assert 
		assertThat(recipe.getIngredients()).contains(ingredient);
	}
	
	@Test
	public void test_add_instruction() {
		//arrange 
		Instruction instruction = new Instruction();
		
		//act
		recipe.addInstruction(instruction);
		
		//assert 
		assertThat(recipe.getInstructions()).contains(instruction);
	}

}
