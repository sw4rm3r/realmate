package com.realmate.engine.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "personalities")
public class Personality {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(length = 20)
    @NotNull
    private String personality;

    @Column(length = 20)
    @NotNull
    private String variant;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private String quizDate;

    @Column(length = 10)
    @NotNull
    private Integer mindScore;

    @Column(length = 10)
    @NotNull
    private Integer energyScore;

    @Column(length = 10)
    @NotNull
    private Integer natureScore;

    @Column(length = 10)
    @NotNull
    private Integer tacticalScore;

    @Column(length = 10)
    @NotNull
    private Integer identityScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public String getQuizDate() {
        return quizDate;
    }

    public void setQuizDate(String quizDate) {
        this.quizDate = quizDate;
    }

    public Integer getMindScore() {
        return mindScore;
    }

    public void setMindScore(Integer mindScore) {
        this.mindScore = mindScore;
    }

    public Integer getEnergyScore() {
        return energyScore;
    }

    public void setEnergyScore(Integer energyScore) {
        this.energyScore = energyScore;
    }

    public Integer getNatureScore() {
        return natureScore;
    }

    public void setNatureScore(Integer natureScore) {
        this.natureScore = natureScore;
    }

    public Integer getTacticalScore() {
        return tacticalScore;
    }

    public void setTacticalScore(Integer tacticalScore) {
        this.tacticalScore = tacticalScore;
    }

    public Integer getIdentityScore() {
        return identityScore;
    }

    public void setIdentityScore(Integer identityScore) {
        this.identityScore = identityScore;
    }
}
