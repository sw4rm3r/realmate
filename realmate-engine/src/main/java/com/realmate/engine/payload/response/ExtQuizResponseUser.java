package com.realmate.engine.payload.response;

import java.util.List;

public class ExtQuizResponseUser {

    private String personality;
    private String variant;
    private List<Integer> scores;

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }
}
