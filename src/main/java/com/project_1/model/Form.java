package com.project_1.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Form {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID form_id;

  @Column(unique = true)
  private String title;

  @JsonManagedReference
  @OneToMany(mappedBy = "form", orphanRemoval = true)
  private List<Question> questions;

  public Form() {
  }

  public Form(UUID form_id, String title, List<Question> questions) {
    this.form_id = form_id;
    this.title = title;
    this.questions = questions;
  }

  public UUID getForm_id() {
    return form_id;
  }

  public void setForm_id(UUID form_id) {
    this.form_id = form_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public void removeQuestion(Question question) {
    this.questions.remove(question);
    question.setForm(null);
  }
}
