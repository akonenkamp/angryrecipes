package com.libertymutual.goforcode.angryrecipe.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String title;
    
    @Column(nullable=false)
    private String description;
    
    @Column(nullable=false)
    private int minutes;
    
    @Column(nullable=true)
    private String recipePictureURL;
    
    @OneToMany(mappedBy="recipe", cascade=CascadeType.ALL)
    private List<Ingredient> ingredients;
    
    @OneToMany(mappedBy="recipe", cascade=CascadeType.ALL)
    private List<Instruction> instructions;

    public Recipe() {
    }

    public Recipe(String title, String description, int minutes, List<Ingredient> ingredients, List<Instruction> instructions) {
        this.title = title;
        this.description = description;
        this.minutes = minutes;
        this.ingredients = ingredients;
        this.instructions = instructions;
        
        for (Instruction inst : instructions) {
            inst.setRecipe(this);
        }
        
        for (Ingredient ing : ingredients) {
            ing.setRecipe(this);
        }
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<Ingredient>();
        }
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }

    public void addInstruction(Instruction instruction) {
        if (instructions == null) {
            instructions = new ArrayList<Instruction>();
        }
        instructions.add(instruction);
        instruction.setRecipe(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

	public String getRecipePictureURL() {
		return recipePictureURL;
	}

	public void setRecipePictureURL(String recipePictureURL) {
		this.recipePictureURL = recipePictureURL;
	}

}
