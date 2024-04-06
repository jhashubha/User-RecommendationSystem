package com.recommendationsystem.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.recommendationsystem.user.enums.RecommendationTypes;
import com.recommendationsystem.user.model.Review;
import com.recommendationsystem.user.req.payload.ReviewReqPayload;
import com.recommendationsystem.user.service.ReviewService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

	@Mock
	private ReviewService reviewService;

	@InjectMocks
	private ReviewController reviewController;

	@Test
	public void testCheckUserRecommendation() throws Exception {
		when(reviewService.checkUserRecommendation(Mockito.any())).thenReturn(RecommendationTypes.HIGHLY_RELEVENT);

		ReviewReqPayload reviewReqPayload = new ReviewReqPayload();
		reviewReqPayload.setCoworkers(true);
		when(reviewService.checkUserRecommendation(reviewReqPayload)).thenReturn(RecommendationTypes.HIGHLY_RELEVENT);

		RecommendationTypes result = reviewController.checkUserRecommendation(reviewReqPayload);

		assertEquals(RecommendationTypes.HIGHLY_RELEVENT, result);

	}

	@Test
	public void testGetAllReviews() {
		List<Review> mockReviews = new ArrayList<>();
		when(reviewService.getAllReviews()).thenReturn(mockReviews);
		List<Review> result = reviewController.getAllReviews();
		assertEquals(mockReviews, result);
	}

}
