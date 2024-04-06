package com.recommendationsystem.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recommendationsystem.user.enums.RecommendationTypes;
import com.recommendationsystem.user.model.Review;
import com.recommendationsystem.user.req.payload.ReviewReqPayload;
import com.recommendationsystem.user.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	@PostMapping("/employee-review")
	public RecommendationTypes checkUserRecommendation(@RequestBody ReviewReqPayload review) {
		return reviewService.checkUserRecommendation(review);
	}

	@GetMapping("/retrieve-all-reviews")
	public List<Review> getAllReviews() {
		return reviewService.getAllReviews();
	}

}