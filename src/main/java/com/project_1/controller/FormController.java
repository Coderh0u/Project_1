package com.project_1.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project_1.model.Form;
import com.project_1.repository.FormRepo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

@RestController
public class FormController {
  private final FormRepo formRepo;

  public FormController(FormRepo formRepo) {
    this.formRepo = formRepo;
  }

  @PostMapping("/form/new")
  public ResponseEntity<?> newForm(@RequestBody String formTitle) {
    try {
      Form newForm = new Form();
      newForm.setTitle(formTitle);
      return ResponseEntity.status(200).body(this.formRepo.save(newForm));
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Failed to create form");
    }
  }

  @DeleteMapping("/form/delete")
  public ResponseEntity<?> delForm(@RequestParam UUID form_id) {
    try {
      Optional<Form> findForm = this.formRepo.findById(form_id);
      if (findForm.isPresent()) {
        Form deleteForm = findForm.get();
        formRepo.delete(deleteForm);
        return ResponseEntity.ok("Form deleted successfully");
      } else {
        return ResponseEntity.status(404).body("Form cannot be found");
      }
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Unable to delete form");
    }
  }

  @PostMapping("/form/{formId}")
  public ResponseEntity<?> findForm(@PathVariable UUID formId) {
    try {
      Optional<Form> findForm = formRepo.findById(formId);
      if (findForm.isPresent()) {
        Form foundForm = findForm.get();
        return ResponseEntity.ok(foundForm);
      } else {
        return ResponseEntity.status(404).body("Cannot find form");
      }
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Cannot find form");
    }
  }
}
