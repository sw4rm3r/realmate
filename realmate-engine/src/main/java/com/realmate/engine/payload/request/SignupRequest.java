package com.realmate.engine.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realmate.engine.models.Gender;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 3, max = 20)
    private String surname;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdate;

    private String propic;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 15)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 15)
    private Gender genderPreference;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
    public String getName() {
        return name;
    }
 
    public void setName(String username) {
        this.name = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPropic() {
        return propic;
    }

    public void setPropic(String propic) {
        this.propic = propic;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGenderPreference() {
        return genderPreference;
    }

    public void setGenderPreference(Gender genderPreference) {
        this.genderPreference = genderPreference;
    }
}
