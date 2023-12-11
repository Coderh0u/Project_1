package com.project_1.resources;

import java.util.List;
import java.util.UUID;

public class FormReq {
  private UUID form_id;
  private String title;
  private List<String> questions;

  public FormReq() {
  }

  public FormReq(UUID form_id, String title, List<String> questions) {
    this.form_id = form_id;
    this.title = title;
    this.questions = questions;
  }

  public FormReq(String title, List<String> questions) {
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

  public List<String> getQuestions() {
    return questions;
  }

  public void setQuestions(List<String> questions) {
    this.questions = questions;
  }

}
