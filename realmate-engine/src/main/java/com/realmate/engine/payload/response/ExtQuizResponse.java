package com.realmate.engine.payload.response;

import java.lang.reflect.Array;
import java.util.List;


public class ExtQuizResponse {

    private Boolean loggedIn;
    private ExtQuizResponseUser user;
    private List<Object> notifications;
    private List<Object> updates;
    private List<Object> messages;

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public ExtQuizResponseUser getUser() {
        return user;
    }

    public void setUser(ExtQuizResponseUser user) {
        this.user = user;
    }
}
