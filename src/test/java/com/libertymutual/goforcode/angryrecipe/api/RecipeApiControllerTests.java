package com.libertymutual.goforcode.angryrecipe.api;

import static org.mockito.Mockito.mock;

import org.junit.Before;

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
	
}
