package com.project_1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_1.model.Form;

public interface FormRepo extends JpaRepository<Form, UUID> {
  
}
