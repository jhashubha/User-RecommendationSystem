package com.recommendationsystem.user.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.recommendationsystem.user.enums.SkillGroups;
import com.recommendationsystem.user.enums.Teams;

@Table(name = "User")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Enumerated(EnumType.STRING)
	private SkillGroups skillGroup;
	private int yearsOfExperience;

	@Enumerated(EnumType.STRING)
	private Teams team;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setName(String name) {
		this.name = name;
	}

	public SkillGroups getSkillGroup() {
		return skillGroup;
	}

	public void setSkillGroup(SkillGroups skillGroup) {
		this.skillGroup = skillGroup;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public Teams getTeam() {
		return team;
	}

	public void setTeam(Teams team) {
		this.team = team;
	}

}
