package com.project_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_1.model.Response;

public interface ResponseRepo extends JpaRepository<Response, Long> {

}
