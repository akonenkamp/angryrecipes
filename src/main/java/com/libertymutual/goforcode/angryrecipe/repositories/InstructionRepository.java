package com.libertymutual.goforcode.angryrecipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libertymutual.goforcode.angryrecipe.models.Instruction;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {

}
