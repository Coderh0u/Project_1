package com.project_1.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project_1.model.Form;
import com.project_1.model.Question;
import com.project_1.model.Response;
import com.project_1.repository.FormRepo;
import com.project_1.repository.QuestionRepo;
import com.project_1.repository.ResponseRepo;
import com.project_1.resources.FormReq;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
public class FormController {
  private final FormRepo formRepo;
  private final QuestionRepo questionRepo;
  private final ResponseRepo responseRepo;

  public FormController(FormRepo formRepo, QuestionRepo questionRepo, ResponseRepo responseRepo) {
    this.formRepo = formRepo;
    this.questionRepo = questionRepo;
    this.responseRepo = responseRepo;
  }

  // create new
  @PutMapping("/form/new")
  public ResponseEntity<?> newForm(@RequestBody FormReq request) {
    try {
      Form newForm = new Form();
      newForm.setTitle(request.getTitle());

      List<Question> questions = new ArrayList<>();
      for (String questionString : request.getQuestions()) {
        Question question = new Question();
        question.setQuestion(questionString);
        question.setForm(newForm);
        questions.add(question);
      }
      newForm.setQuestions(questions);

      return ResponseEntity.status(200).body(this.formRepo.save(newForm));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body("Failed to create form");
    }
  }

  // delete
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

  // get one form
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

  // update form
  @PatchMapping("/form/update")
  public ResponseEntity<?> updateForm(@RequestBody FormReq request) {
    try {
      Optional<Form> findForm = formRepo.findById(request.getForm_id());
      if (findForm.isPresent()) {
        Form updatedForm = findForm.get();
        updatedForm.setTitle(request.getTitle());

        System.out.println(1);

        for (Question question : new ArrayList<>(updatedForm.getQuestions())) {
          if (!request.getQuestions().contains(question.getQuestion())) {
            updatedForm.removeQuestion(question);
            questionRepo.delete(question);
          }
        }

        System.out.println(2);

        formRepo.saveAndFlush(updatedForm);

        System.out.println(3);

        List<Question> newQuestions = new ArrayList<>();

        System.out.println(4);

        for (String questionString : request.getQuestions()) {
          Question question = new Question();
          question.setQuestion(questionString);
          question.setForm(updatedForm);
          newQuestions.add(question);
        }

        System.out.println(5);

        updatedForm.setQuestions(newQuestions);

        System.out.println(6);

        formRepo.save(updatedForm);

        System.out.println(7);

        return ResponseEntity.status(200).body(updatedForm);
      } else {
        return ResponseEntity.status(404).body("Cannot find form");

      }
    } catch (Exception e) {
      return ResponseEntity.status(500).body("updating failed: " + e.getMessage());
    }
  }

  // testing purpose
  @GetMapping("/form/all")
  public ResponseEntity<?> getAllForm() {
    List<Form> allForm = formRepo.findAll();
    return ResponseEntity.status(200).body(allForm);
  }

}
