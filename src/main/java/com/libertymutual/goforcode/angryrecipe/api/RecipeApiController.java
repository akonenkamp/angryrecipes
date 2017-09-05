package com.libertymutual.goforcode.angryrecipe.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("{id}") 
	public Recipe getOne(@PathVariable long id) throws StuffNotFoundException {
		Recipe recipe = recipeRepo.findOne(id);
		if(recipe == null) {
			throw new StuffNotFoundException();
		}
		return recipe;	
	}
	
	@PostMapping("")
	public Recipe createOne(@RequestBody Recipe recipe) {
		return recipeRepo.save(recipe);
	}
	
	@DeleteMapping("{id}") 
	public Recipe deleteOne(@PathVariable long id) {
		try { 
			Recipe recipe = recipeRepo.findOne(id);
			recipeRepo.delete(id);
			return recipe;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	@PutMapping("{id}")
	public Recipe update(@RequestBody Recipe recipe, @PathVariable long id) {
		recipe.setId(id);
		return recipeRepo.save(recipe);
	}

}
