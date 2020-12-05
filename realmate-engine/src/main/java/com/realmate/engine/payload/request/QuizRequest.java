package com.realmate.engine.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class QuizRequest {

    @NotNull
    @JsonProperty("questions")
    private List<QuizRequestBase> questions;

    public List<QuizRequestBase> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizRequestBase> questions) {
        this.questions = questions;
    }
}
