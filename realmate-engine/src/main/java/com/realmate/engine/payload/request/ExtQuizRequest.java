package com.realmate.engine.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExtQuizRequest {
    private List<ExtQuizRequestBase> questions;
    private Integer version;
    private String gender;
    private String invitecode;



    public List<ExtQuizRequestBase> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ExtQuizRequestBase> questions) {
        this.questions = questions;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }
}
