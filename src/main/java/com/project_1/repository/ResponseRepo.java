package com.project_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_1.model.Question;
import com.project_1.model.Response;
import java.util.List;

public interface ResponseRepo extends JpaRepository<Response, Long> {
  List<Response> findByQuestion(Question question);
}
