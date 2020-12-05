package com.realmate.engine.models;

import javax.persistence.*;

@Entity
@Table(name = "test_questions")
public class Question {
    @Id
    private Integer id;

    @Column(length = 200)
    private String question;

    @Column(length = 3)
    private String question_category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_category() {
        return question_category;
    }

    public void setQuestion_category(String question_category) {
        this.question_category = question_category;
    }
}
