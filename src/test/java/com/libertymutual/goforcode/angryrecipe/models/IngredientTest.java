package com.libertymutual.goforcode.angryrecipe.models;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class IngredientTest {
	
	private Ingredient ingredient;
	
	@Before 
	public void setUp() {
		ingredient = new Ingredient();
	}
	
	
	@Test
	public void test_setId() {
		//arrange
		Long id = 3l;
		ingredient.setId(id);
		
		//act
		Long actual = ingredient.getId();
		
		//assert
		assertThat(actual).isSameAs(3l);
		
		
	}
	
	@Test
	public void get_ingredient_name() {
		//arrange 
		String ingredientName = "Pepper";
		ingredient.setName(ingredientName);
		
		//act 
		String actual = ingredient.getName();
		
		//assert
		assertThat(actual).isEqualTo(ingredientName);
		
		
	}
	
//	@Test 
//	public void 
//
}
