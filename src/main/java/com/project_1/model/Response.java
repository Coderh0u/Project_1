package com.project_1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Response {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long response_id;

  private String response;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;

  public Response() {
  }

  public Response(Long response_id, String response, Question question) {
    this.response_id = response_id;
    this.response = response;
    this.question = question;
  }

  public Long getResponse_id() {
    return response_id;
  }

  public void setResponse_id(Long response_id) {
    this.response_id = response_id;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }
}
