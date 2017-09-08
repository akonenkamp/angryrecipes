package com.libertymutual.goforcode.angryrecipe.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.angryrecipe.models.Ingredient;
import com.libertymutual.goforcode.angryrecipe.models.Instruction;
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
		// arrange
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(new Recipe());
		recipes.add(new Recipe());
		when(recipeRepo.findAll()).thenReturn(recipes);

		// act
		List<Recipe> actual = controller.getAll(null);

		// assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(recipes.get(0));
		verify(recipeRepo).findAll();

	}

	@Test

	public void test_get_one_returns_recipes_from_repo() throws StuffNotFoundException {
		// arrange
		Recipe chickenCurry = new Recipe();
		when(recipeRepo.findOne(556l)).thenReturn(chickenCurry);

		// act
		Recipe actual = controller.getOne(556l);

		// assert
		assertThat(actual).isSameAs(chickenCurry);
		verify(recipeRepo).findOne(556l);
	}

	@Test
	public void test_getone_throws_didnt_find_it_when_no_recipe_is_returned() {
		try {
			controller.getOne(1l);
			fail("nothing to see here");
		} catch (StuffNotFoundException snfe) {
		}
	}

	@Test

	public void test_delete_returns_recipe_when_foud() {
		// arrange
		Recipe recipe = new Recipe();
		when(recipeRepo.findOne(3l)).thenReturn(recipe);
		// act
		Recipe actual = controller.deleteOne(3l);
		// assert
		assertThat(recipe).isSameAs(actual);
		verify(recipeRepo).delete(3l);

	}

	@Test
	public void test_delete_returns_catch_throw_exception() {
		// arrange
		when(recipeRepo.findOne(3l)).thenThrow(new EmptyResultDataAccessException(0));

		// act
		Recipe actual = controller.deleteOne(3l);

		// assert
		assertThat(actual).isNull();
		verify(recipeRepo).findOne(3l);
	}

	@Test

	public void test_the_create_recipe_and_add_to_repo() {
		// arrange
		Recipe recipe = new Recipe();
		when(recipeRepo.save(recipe)).thenReturn(recipe);

		// act
		Recipe actualRecipe = controller.createOne(recipe);

		// assert
		assertThat(recipe).isSameAs(actualRecipe);
		verify(recipeRepo).save(recipe);
	}

	@Test
	public void test_that_recipe_updates_and_saves_to_recipe_repo() {
		// arrange
		Recipe recipe = new Recipe();
		when(recipeRepo.save(recipe)).thenReturn(recipe);

		// act
		Recipe actualRecipe = controller.update(recipe, 4l);

		// assert
		assertThat(actualRecipe.getId()).isSameAs(recipe.getId());
		verify(recipeRepo).save(recipe);
	}

	@Test

	public void test_that_associate_an_ingredient_save_to_recipe_repo() {

		// arrange
		Recipe recipe = new Recipe();
		when(recipeRepo.findOne(3l)).thenReturn(recipe);
		Ingredient ingredient = new Ingredient();
		ingredient.setId(2l);
		when(ingredientRepo.findOne(2l)).thenReturn(ingredient);

		// act
		Recipe actualRecipe = controller.associateAnIngredient(3l, ingredient);
		// assert

		assertThat(actualRecipe).isSameAs(recipe);
		assertThat(actualRecipe.getIngredients()).contains(ingredient);
		verify(recipeRepo).save(recipe);
	}

	@Test
	public void test_that_associate_the_instruction_save_to_repo() {
		// arrange
		Recipe recipe = new Recipe();
		when(recipeRepo.findOne(3l)).thenReturn(recipe);
		Instruction instruction = new Instruction();
		instruction.setId(2l);
		when(instructionRepo.findOne(2l)).thenReturn(instruction);

		// act
		Recipe actualRecipe = controller.associateAnInstruction(3l, instruction);

		// assert
		assertThat(actualRecipe).isSameAs(recipe);
		assertThat(actualRecipe.getInstructions()).contains(instruction);
		verify(recipeRepo).save(recipe);
	}

	@Test

	public void test_update_instruction_edit_instruction_to_repo() {

		// arrange
		Recipe recipe = new Recipe();
		Instruction instruction = new Instruction();
		when(recipeRepo.findOne(3l)).thenReturn(recipe);
		when(recipeRepo.save(recipe)).thenReturn(recipe);
		when(instructionRepo.save(instruction)).thenReturn(instruction);

		// act
		Recipe actual = controller.updateInstruction(instruction, 3l, 1l);

		// assert
		assertThat(instruction.getId()).isEqualTo(1l);
		assertThat(instruction.getRecipe()).isSameAs(recipe);

		verify(instructionRepo).save(instruction);
		verify(recipeRepo, times(2)).findOne(3l);
	}

	@Test

	public void test_update_ingredient_edit_ingredient_to_repo() {

		// arrange
		Recipe recipe = new Recipe();
		Ingredient ingredient = new Ingredient();
		when(recipeRepo.findOne(3l)).thenReturn(recipe);
		when(recipeRepo.save(recipe)).thenReturn(recipe);
		when(ingredientRepo.save(ingredient)).thenReturn(ingredient);

		// act
		Recipe actual = controller.updateIngredient(ingredient, 3l, 1l);

		// assert
		assertThat(ingredient.getId()).isEqualTo(1l);
		assertThat(ingredient.getRecipe()).isSameAs(recipe);

		verify(ingredientRepo).save(ingredient);
		verify(recipeRepo, times(2)).findOne(3l);
	}

	@Test
	public void test_delete_an_ingredient_throws_exception() {
		// arrange

		// Ingredient ingredient = new Ingredient();
		when(recipeRepo.findOne(3l)).thenThrow(new EmptyResultDataAccessException(0));
		// when(ingredientRepo.findOne(3l)).thenReturn(ingredient);

		// act
		Recipe actual = controller.deleteAnIngredient(3l, 4l);

		// assert
		assertThat(actual).isNull();
		verify(recipeRepo).findOne(3l);
	}

	@Test
	public void test_delete_an_instruction_throws_exception() {
		// arrange

		// Ingredient ingredient = new Ingredient();
		when(recipeRepo.findOne(3l)).thenThrow(new EmptyResultDataAccessException(0));
		// when(ingredientRepo.findOne(3l)).thenReturn(ingredient);

		// act
		Recipe actual = controller.deleteAnInstruction(3l, 4l);

		// assert
		assertThat(actual).isNull();
		verify(recipeRepo).findOne(3l);
	}
	
	@Test
	public void test_delete_an_ingredient_from_repo() {
		//arrange
		Recipe recipe = new Recipe();
		when(recipeRepo.findOne(3l)).thenReturn(recipe);
		//act
		Recipe actual = controller.deleteAnIngredient(3l, 4l);
		//assert
		verify(recipeRepo).findOne(3l);
		verify(ingredientRepo).delete(4l);
		verify(recipeRepo).flush();
		assertThat(actual).isSameAs(recipe);
	}
	
	@Test
	
	public void test_delete_an_instruction_from_repo() {
		//arrange
				Recipe recipe = new Recipe();
				when(recipeRepo.findOne(3l)).thenReturn(recipe);
				//act
				Recipe actual = controller.deleteAnInstruction(3l, 4l);
				//assert
				verify(recipeRepo).findOne(3l);
				verify(instructionRepo).delete(4l);
				verify(recipeRepo).flush();
				assertThat(actual).isSameAs(recipe);
	}

}
