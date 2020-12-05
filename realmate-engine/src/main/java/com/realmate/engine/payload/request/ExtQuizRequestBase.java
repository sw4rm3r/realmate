package com.realmate.engine.payload.request;

public class ExtQuizRequestBase {
    private String text;
    private Integer answer;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
