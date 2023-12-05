package com.project_1.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Question {
  @Id
  private Long question_id;

  @Column(unique = true)
  private String question;

  @ManyToOne
  @JoinColumn(name = "form_id")
  private Form form;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  private List<Response> responses;

  public Question() {
  }

  public Question(String question, Form form, List<Response> responses) {
    this.question = question;
    this.form = form;
    this.responses = responses;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public Form getForm() {
    return form;
  }

  public void setForm(Form form) {
    this.form = form;
  }

  public List<Response> getResponses() {
    return responses;
  }

  public void setResponses(List<Response> responses) {
    this.responses = responses;
  }
}
