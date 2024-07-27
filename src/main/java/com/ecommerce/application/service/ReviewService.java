package com.ecommerce.application.service;

import com.ecommerce.application.dto.ReviewDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewService {

    ReviewDto createReview(ReviewDto reviewDto);
    Optional<ReviewDto> getReviewById(UUID reviewId);
    List<ReviewDto> getAllReviews();
    ReviewDto updateReview(UUID reviewId, ReviewDto reviewDto);
    void deleteReview(UUID reviewId);
}
