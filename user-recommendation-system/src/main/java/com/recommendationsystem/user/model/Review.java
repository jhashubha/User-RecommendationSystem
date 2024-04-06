package com.recommendationsystem.user.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.recommendationsystem.user.enums.RecommendationTypes;

@Table(name = "Review")
@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long reviewerUserId;

	private Long revieweeUserId;

	private String description;
	@Enumerated(EnumType.STRING)
	private RecommendationTypes recommendationTypes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RecommendationTypes getRecommendationTypes() {
		return recommendationTypes;
	}

	public void setRecommendationTypes(RecommendationTypes recommendationTypes) {
		this.recommendationTypes = recommendationTypes;
	}

	public Long getReviewerUserId() {
		return reviewerUserId;
	}

	public void setReviewerUserId(Long reviewerUserId) {
		this.reviewerUserId = reviewerUserId;
	}

	public Long getRevieweeUserId() {
		return revieweeUserId;
	}

	public void setRevieweeUserId(Long revieweeUserId) {
		this.revieweeUserId = revieweeUserId;
	}

}
