package com.project_1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_1.model.Question;

public interface QuestionRepo extends JpaRepository<Question, UUID> {
  
}
