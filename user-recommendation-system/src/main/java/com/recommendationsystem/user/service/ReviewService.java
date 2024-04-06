package com.recommendationsystem.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recommendationsystem.user.enums.RecommendationTypes;
import com.recommendationsystem.user.exception.ReviewAlreadyExistException;
import com.recommendationsystem.user.exception.SelfReviewException;
import com.recommendationsystem.user.exception.UserNotFoundException;
import com.recommendationsystem.user.model.Review;
import com.recommendationsystem.user.model.User;
import com.recommendationsystem.user.repository.ReviewRepository;
import com.recommendationsystem.user.req.payload.ReviewReqPayload;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private UserService userService;

	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}


	public RecommendationTypes checkUserRecommendation(ReviewReqPayload review) {
		if (review.getRevieweeEmail().equalsIgnoreCase(review.getReviewerEmail())) {
			throw new ReviewAlreadyExistException();
		}
		User revieweeDetails = userService.getUserByEmailId(review.getRevieweeEmail());
		User reviewerDetails = userService.getUserByEmailId(review.getReviewerEmail());

		Optional<Review> data = reviewRepository.findByReviewerUserIdAndRevieweeUserId(reviewerDetails.getId(),
				revieweeDetails.getId());
		if (data.isPresent()) {
			throw new ReviewAlreadyExistException("You have already reviewed this user");
		}
		if (revieweeDetails.equals(reviewerDetails)) {
			throw new SelfReviewException("Reviewer and Reviewee cannot be same");
		}
		if (revieweeDetails != null && reviewerDetails != null) {

			if (revieweeDetails.getTeam().equals(reviewerDetails.getTeam())) {

				if (revieweeDetails.getSkillGroup().name().equals(reviewerDetails.getSkillGroup().name())
						&& revieweeDetails.getYearsOfExperience() == reviewerDetails.getYearsOfExperience()
						&& review.getCoworkers()) {
					Review reviewData = new Review();
					reviewData.setDescription(review.getDiscription());
					reviewData.setRecommendationTypes(RecommendationTypes.HIGHLY_RELEVENT);
					reviewData.setRevieweeUserId(revieweeDetails.getId());
					reviewData.setReviewerUserId(reviewerDetails.getId());

					reviewRepository.save(reviewData);
					return RecommendationTypes.HIGHLY_RELEVENT;

				}

				if (revieweeDetails.getSkillGroup().name().equals(reviewerDetails.getSkillGroup().name())
						&& revieweeDetails.getYearsOfExperience() - reviewerDetails.getYearsOfExperience() >= 5
						&& review.getCoworkers()) {
					Review reviewData = new Review();
					reviewData.setDescription(review.getDiscription());
					reviewData.setRecommendationTypes(RecommendationTypes.MODERATELY_REVELENT);
					reviewData.setRevieweeUserId(revieweeDetails.getId());
					reviewData.setReviewerUserId(reviewerDetails.getId());

					reviewRepository.save(reviewData);
					return RecommendationTypes.MODERATELY_REVELENT;

				}

				if (revieweeDetails.getSkillGroup().name().equals(reviewerDetails.getSkillGroup().name())
						&& revieweeDetails.getYearsOfExperience() < reviewerDetails.getYearsOfExperience()
						&& review.getCoworkers()) {
					Review reviewData = new Review();
					reviewData.setDescription(review.getDiscription());
					reviewData.setRecommendationTypes(RecommendationTypes.HIGHLY_RELEVENT);
					reviewData.setRevieweeUserId(revieweeDetails.getId());
					reviewData.setReviewerUserId(reviewerDetails.getId());

					reviewRepository.save(reviewData);
					return RecommendationTypes.HIGHLY_RELEVENT;

				}
				if (revieweeDetails.getSkillGroup().name().equals(reviewerDetails.getSkillGroup().name())
						&& revieweeDetails.getYearsOfExperience() == reviewerDetails.getYearsOfExperience()
						&& !review.getCoworkers()) {
					Review reviewData = new Review();
					reviewData.setDescription(review.getDiscription());
					reviewData.setRecommendationTypes(RecommendationTypes.HIGHLY_RELEVENT);
					reviewData.setRevieweeUserId(revieweeDetails.getId());
					reviewData.setReviewerUserId(reviewerDetails.getId());

					reviewRepository.save(reviewData);
					return RecommendationTypes.HIGHLY_RELEVENT;

				}

				if (!revieweeDetails.getSkillGroup().equals(reviewerDetails.getSkillGroup())
						&& revieweeDetails.getYearsOfExperience() == reviewerDetails.getYearsOfExperience()
						&& review.getCoworkers()) {
					Review reviewData = new Review();
					reviewData.setDescription(review.getDiscription());
					reviewData.setRecommendationTypes(RecommendationTypes.MODERATELY_REVELENT);
					reviewData.setRevieweeUserId(revieweeDetails.getId());
					reviewData.setReviewerUserId(reviewerDetails.getId());

					reviewRepository.save(reviewData);
					return RecommendationTypes.MODERATELY_REVELENT;

				}
				if (revieweeDetails.getYearsOfExperience() > reviewerDetails.getYearsOfExperience()) {
					Review reviewData = new Review();
					reviewData.setDescription(review.getDiscription());
					reviewData.setRecommendationTypes(RecommendationTypes.LEAST_REVELENT);
					reviewData.setRevieweeUserId(revieweeDetails.getId());
					reviewData.setReviewerUserId(reviewerDetails.getId());

					reviewRepository.save(reviewData);
					return RecommendationTypes.LEAST_REVELENT;
				}
			} else {

				Review reviewData = new Review();
				reviewData.setDescription(review.getDiscription());
				reviewData.setRecommendationTypes(RecommendationTypes.LEAST_REVELENT);
				reviewData.setRevieweeUserId(revieweeDetails.getId());
				reviewData.setReviewerUserId(reviewerDetails.getId());

				reviewRepository.save(reviewData);
				return RecommendationTypes.LEAST_REVELENT;

			}

		}
		throw new UserNotFoundException("User not found");

	}

}
