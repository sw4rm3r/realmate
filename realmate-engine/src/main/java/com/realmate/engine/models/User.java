package com.realmate.engine.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "name"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String name;

	@NotBlank
	@Size(max = 20)
	private String surname;

	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;

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
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Personality personality;

	public User() {
	}

	public User(String username, String surname, Date birthDate, String propic, String email,Gender gender, Gender genderPreference, String password) {
		this.name = username;
		this.email = email;
		this.password = password;
		this.surname = surname;
		this.birthDate = birthDate;
		this.propic = propic;
		this.gender = gender;
		this.genderPreference = genderPreference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPropic() {
		return propic;
	}

	public void setPropic(String propic) {
		this.propic = propic;
	}

	public Personality getPersonality() {
		return personality;
	}

	public void setPersonality(Personality personality) {
		this.personality = personality;
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
