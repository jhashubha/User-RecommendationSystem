package com.recommendationsystem.user.req.payload;

public class ReviewReqPayload {

	private String reviewerEmail;

	private String revieweeEmail;

	private Boolean coworkers;
	private String discription;

	private int rating;

	public String getReviewerEmail() {
		return reviewerEmail;
	}

	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}

	public String getRevieweeEmail() {
		return revieweeEmail;
	}

	public void setRevieweeEmail(String revieweeEmail) {
		this.revieweeEmail = revieweeEmail;
	}

	public Boolean getCoworkers() {
		return coworkers;
	}

	public void setCoworkers(Boolean coworkers) {
		this.coworkers = coworkers;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
