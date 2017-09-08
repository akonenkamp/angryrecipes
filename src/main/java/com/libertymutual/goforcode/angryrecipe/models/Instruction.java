package com.libertymutual.goforcode.angryrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity

public class Instruction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String instruction;
	
	@Column(nullable=true)
	private String instructionPictureURL;
	
	private Integer stepNumber;
	
	@ManyToOne
	private Recipe recipe;
	
	
	public Instruction() {}
	
	public Instruction(String instruction, Integer stepNumber) {
		this.instruction = instruction;
		this.stepNumber = stepNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getInstructionPictureURL() {
		return instructionPictureURL;
	}

	public void setInstructionPictureURL(String instructionPictureURL) {
		this.instructionPictureURL = instructionPictureURL;
	}

	public Integer getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(Integer stepNumber) {
		this.stepNumber = stepNumber;
	}

}
