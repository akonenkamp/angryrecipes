package com.libertymutual.goforcode.angryrecipe.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.angryrecipe.models.Ingredient;
import com.libertymutual.goforcode.angryrecipe.models.Instruction;
import com.libertymutual.goforcode.angryrecipe.models.Recipe;
import com.libertymutual.goforcode.angryrecipe.repositories.IngredientRepository;
import com.libertymutual.goforcode.angryrecipe.repositories.InstructionRepository;
import com.libertymutual.goforcode.angryrecipe.repositories.RecipeRepository;

@RestController 
@RequestMapping("/recipes")
public class RecipeApiController {
	
	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;
	
	
	public RecipeApiController(RecipeRepository recipeRepo, IngredientRepository ingredientRepo, InstructionRepository instructionRepo) {
		this.recipeRepo = recipeRepo;
		this.ingredientRepo = ingredientRepo;
		this.instructionRepo = instructionRepo;
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient("Cheese", "Cups", 3d));
		ingredients.add(new Ingredient("Sauce", "Cups", 3d));
		ingredients.add(new Ingredient("Pepperoni", "Cups", 3d));
		ingredients.add(new Ingredient("Pineapple", "Cups", 2d));
		
		ingredientRepo.save(ingredients);
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(new Instruction("Flatten dough"));
		instructions.add(new Instruction("Put sauce on dough"));
		instructions.add(new Instruction("Put toppings on sauce"));
		instructions.add(new Instruction("Bake at 350 for 25 minutes"));
		
		instructionRepo.save(instructions);
		
		recipeRepo.save(new Recipe("Pineapple Pepperoni Pizza", "Pizza with pineapples and pepperonis", 40, ingredients, instructions));
		
	}
	
	@GetMapping("")
	public List<Recipe> getAll() {
		return recipeRepo.findAll();
	}

}