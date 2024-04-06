package com.recommendationsystem.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.recommendationsystem.user.enums.RecommendationTypes;
import com.recommendationsystem.user.enums.SkillGroups;
import com.recommendationsystem.user.enums.Teams;
import com.recommendationsystem.user.exception.ReviewAlreadyExistException;
import com.recommendationsystem.user.model.Review;
import com.recommendationsystem.user.model.User;
import com.recommendationsystem.user.repository.ReviewRepository;
import com.recommendationsystem.user.req.payload.ReviewReqPayload;

class ReviewServiceTest {

	@InjectMocks
	private ReviewService reviewService;

	@Mock
	private ReviewRepository reviewRepository;

	@Mock
	private UserService userService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCheckUserRecommendation_HighlyRelevant() {
		ReviewReqPayload reviewReqPayload = new ReviewReqPayload();
		reviewReqPayload.setReviewerEmail("reviewer@example.com");
		reviewReqPayload.setRevieweeEmail("reviewee@example.com");
		reviewReqPayload.setCoworkers(true);
		User reviewer = new User();
		reviewer.setId(1L);
		reviewer.setTeam(Teams.DEVELOPER);
		reviewer.setSkillGroup(SkillGroups.DEVOPS);
		reviewer.setYearsOfExperience(5);
		when(userService.getUserByEmailId("reviewer@example.com")).thenReturn(reviewer);
		User reviewee = new User();
		reviewee.setId(2L);
		reviewee.setTeam(Teams.DEVELOPER);
		reviewee.setSkillGroup(SkillGroups.DEVOPS);
		reviewee.setYearsOfExperience(5);
		when(userService.getUserByEmailId("reviewee@example.com")).thenReturn(reviewee);
		when(reviewRepository.findByReviewerUserIdAndRevieweeUserId(1L, 2L)).thenReturn(Optional.empty());
		RecommendationTypes result = reviewService.checkUserRecommendation(reviewReqPayload);
		assertEquals(RecommendationTypes.HIGHLY_RELEVENT, result);
	}

	@Test
	public void testCheckUserRecommendation_MODERATELY_REVELENT() {
		ReviewReqPayload reviewReqPayload = new ReviewReqPayload();
		reviewReqPayload.setReviewerEmail("reviewer@example.com");
		reviewReqPayload.setRevieweeEmail("reviewee@example.com");
		reviewReqPayload.setCoworkers(true);
		User reviewer = new User();
		reviewer.setId(1L);
		reviewer.setTeam(Teams.DEVELOPER);
		reviewer.setSkillGroup(SkillGroups.DEVOPS);
		reviewer.setYearsOfExperience(1);
		when(userService.getUserByEmailId("reviewer@example.com")).thenReturn(reviewer);
		User reviewee = new User();
		reviewee.setId(2L);
		reviewee.setTeam(Teams.DEVELOPER);
		reviewee.setSkillGroup(SkillGroups.DEVOPS);
		reviewee.setYearsOfExperience(10);
		when(userService.getUserByEmailId("reviewee@example.com")).thenReturn(reviewee);
		when(reviewRepository.findByReviewerUserIdAndRevieweeUserId(1L, 2L)).thenReturn(Optional.empty());
		RecommendationTypes result = reviewService.checkUserRecommendation(reviewReqPayload);
		assertEquals(RecommendationTypes.MODERATELY_REVELENT, result);
	}

	@Test
	public void testCheckUserRecommendation_HighlyRelevantcase2() {
		ReviewReqPayload reviewReqPayload = new ReviewReqPayload();
		reviewReqPayload.setReviewerEmail("reviewer@example.com");
		reviewReqPayload.setRevieweeEmail("reviewee@example.com");
		reviewReqPayload.setCoworkers(true);
		User reviewer = new User();
		reviewer.setId(1L);
		reviewer.setTeam(Teams.DEVELOPER);
		reviewer.setSkillGroup(SkillGroups.DEVOPS);
		reviewer.setYearsOfExperience(5);
		when(userService.getUserByEmailId("reviewer@example.com")).thenReturn(reviewer);
		User reviewee = new User();
		reviewee.setId(2L);
		reviewee.setTeam(Teams.DEVELOPER);
		reviewee.setSkillGroup(SkillGroups.DEVOPS);
		reviewee.setYearsOfExperience(1);
		when(userService.getUserByEmailId("reviewee@example.com")).thenReturn(reviewee);
		when(reviewRepository.findByReviewerUserIdAndRevieweeUserId(1L, 2L)).thenReturn(Optional.empty());
		RecommendationTypes result = reviewService.checkUserRecommendation(reviewReqPayload);
		assertEquals(RecommendationTypes.HIGHLY_RELEVENT, result);
	}

	@Test
	public void testCheckUserRecommendation_MODERATELY_REVELENTcase3() {
		ReviewReqPayload reviewReqPayload = new ReviewReqPayload();
		reviewReqPayload.setReviewerEmail("reviewer@example.com");
		reviewReqPayload.setRevieweeEmail("reviewee@example.com");
		reviewReqPayload.setCoworkers(true);
		User reviewer = new User();
		reviewer.setId(1L);
		reviewer.setTeam(Teams.DEVELOPER);
		reviewer.setSkillGroup(SkillGroups.JAVA);
		reviewer.setYearsOfExperience(1);
		when(userService.getUserByEmailId("reviewer@example.com")).thenReturn(reviewer);
		User reviewee = new User();
		reviewee.setId(2L);
		reviewee.setTeam(Teams.DEVELOPER);
		reviewee.setSkillGroup(SkillGroups.DEVOPS);
		reviewee.setYearsOfExperience(1);
		when(userService.getUserByEmailId("reviewee@example.com")).thenReturn(reviewee);
		when(reviewRepository.findByReviewerUserIdAndRevieweeUserId(1L, 2L)).thenReturn(Optional.empty());
		RecommendationTypes result = reviewService.checkUserRecommendation(reviewReqPayload);
		assertEquals(RecommendationTypes.MODERATELY_REVELENT, result);
	}
	@Test
	public void testCheckUserRecommendation_HIGHLY_RELEVENTcase3() {
		ReviewReqPayload reviewReqPayload = new ReviewReqPayload();
		reviewReqPayload.setReviewerEmail("reviewer@example.com");
		reviewReqPayload.setRevieweeEmail("reviewee@example.com");
		reviewReqPayload.setCoworkers(false);
		User reviewer = new User();
		reviewer.setId(1L);
		reviewer.setTeam(Teams.DEVELOPER);
		reviewer.setSkillGroup(SkillGroups.JAVA);
		reviewer.setYearsOfExperience(1);
		when(userService.getUserByEmailId("reviewer@example.com")).thenReturn(reviewer);
		User reviewee = new User();
		reviewee.setId(2L);
		reviewee.setTeam(Teams.DEVELOPER);
		reviewee.setSkillGroup(SkillGroups.JAVA);
		reviewee.setYearsOfExperience(1);
		when(userService.getUserByEmailId("reviewee@example.com")).thenReturn(reviewee);
		when(reviewRepository.findByReviewerUserIdAndRevieweeUserId(1L, 2L)).thenReturn(Optional.empty());
		RecommendationTypes result = reviewService.checkUserRecommendation(reviewReqPayload);
		assertEquals(RecommendationTypes.HIGHLY_RELEVENT, result);
	}
	
	@Test
	public void testCheckUserRecommendation_LEAST_REVELENT() {
		ReviewReqPayload reviewReqPayload = new ReviewReqPayload();
		reviewReqPayload.setReviewerEmail("reviewer@example.com");
		reviewReqPayload.setRevieweeEmail("reviewee@example.com");
		reviewReqPayload.setCoworkers(false);
		User reviewer = new User();
		reviewer.setId(1L);
		reviewer.setTeam(Teams.SALES);
		reviewer.setSkillGroup(SkillGroups.JAVA);
		reviewer.setYearsOfExperience(1);
		when(userService.getUserByEmailId("reviewer@example.com")).thenReturn(reviewer);
		User reviewee = new User();
		reviewee.setId(2L);
		reviewee.setTeam(Teams.DEVELOPER);
		reviewee.setSkillGroup(SkillGroups.JAVA);
		reviewee.setYearsOfExperience(1);
		when(userService.getUserByEmailId("reviewee@example.com")).thenReturn(reviewee);
		when(reviewRepository.findByReviewerUserIdAndRevieweeUserId(1L, 2L)).thenReturn(Optional.empty());
		RecommendationTypes result = reviewService.checkUserRecommendation(reviewReqPayload);
		assertEquals(RecommendationTypes.LEAST_REVELENT, result);
	}


}
