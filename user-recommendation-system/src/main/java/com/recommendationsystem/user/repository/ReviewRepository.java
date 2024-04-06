package com.recommendationsystem.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recommendationsystem.user.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	Optional<Review> findByReviewerUserIdAndRevieweeUserId(Long id, Long id2);
}