package com.realmate.engine.repository;

import com.realmate.engine.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Question, Integer> {

}
