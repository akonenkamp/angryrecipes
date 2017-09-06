package com.libertymutual.goforcode.angryrecipe.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.angryrecipe.models.Recipe;
import com.libertymutual.goforcode.angryrecipe.repositories.IngredientRepository;
import com.libertymutual.goforcode.angryrecipe.repositories.InstructionRepository;
import com.libertymutual.goforcode.angryrecipe.repositories.RecipeRepository;

public class RecipeApiControllerTests {

	private RecipeRepository recipeRepo;
	private RecipeApiController controller;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;
	
	
	@Before 
	public void setUp() {
		recipeRepo = mock(RecipeRepository.class);
		ingredientRepo = mock(IngredientRepository.class);
		instructionRepo = mock(InstructionRepository.class);
		controller = new RecipeApiController(recipeRepo, ingredientRepo, instructionRepo);
	}
	
	@Test 
	
	public void test_get_all_returns_all_recipes() {
		//arrange
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(new Recipe());
		recipes.add(new Recipe());
		when(recipeRepo.findAll()).thenReturn(recipes);
		
		//act
		List<Recipe> actual = controller.getAll();
		
		
		//assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(recipes.get(0));
		verify(recipeRepo).findAll();
		
	}
	
	@Test 
	
	public void test_get_one_returns_recipes_from_repo() throws StuffNotFoundException  {
		//arrange
		Recipe chickenCurry = new Recipe();
		when(recipeRepo.findOne(556l)).thenReturn(chickenCurry);
		
		//act
		Recipe actual = controller.getOne(556l);
		
		
		//assert
		assertThat(actual).isSameAs(chickenCurry);
		verify(recipeRepo).findOne(556l);
	}
	
	@Test 
	public void test_getone_throws_didnt_find_it_when_no_recipe_is_returned() {
		try {
			controller.getOne(1l);
			fail("nothing to see here"); 
		} catch(StuffNotFoundException snfe) {}
	}
	
	@Test
	
	public void test_delete_returns_recipe_when_foud() {
		//arrange 
		Recipe recipe = new Recipe();
		when(recipeRepo.findOne(3l)).thenReturn(recipe);
		//act
		Recipe actual = controller.deleteOne(3l);
		//assert
		assertThat(recipe).isSameAs(actual);
		verify(recipeRepo).delete(3l);
	
	}
	
	@Test 
	public void test_delete_returns_catch_throw_exception() {
		//arrange
		when(recipeRepo.findOne(3l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//act
		Recipe actual = controller.deleteOne(3l);
		
		//assert
		assertThat(actual).isNull();
		verify(recipeRepo).findOne(3l);
	}
	
	
	@Test
	
	public void test_the_create_recipe_and_add_to_repo() {
		//arrange
		Recipe recipe = new Recipe();
		when (recipeRepo.save(recipe)).thenReturn(recipe);
		
		//act
		Recipe actualRecipe = controller.createOne(recipe);
		
		//assert
		assertThat(recipe).isSameAs(actualRecipe);
		verify(recipeRepo).save(recipe);
	}
	
	@Test
	public void test_that_recipe_updates_and_saves_to_movie_repo() {
		//arrange
		Recipe recipe = new Recipe();
		when(recipeRepo.save(recipe)).thenReturn(recipe);
		
		//act
		Recipe actualRecipe = controller.update(recipe, 4l);
		
		//assert
		assertThat(actualRecipe.getId()).isSameAs(recipe.getId());
		verify(recipeRepo).save(recipe);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}