package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.ReviewDto;
import com.ecommerce.application.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review toReview(ReviewDto reviewDto) {
        if (reviewDto == null) {
            return  null;
        }
        Review review = new Review();
        review.setReviewId(reviewDto.getReviewId());
        review.setUser(new UserMapper().toUser(reviewDto.getUser()));
        review.setProduct(new ProductMapper().toProduct(reviewDto.getProduct()));
        review.setReviewText(reviewDto.getReviewText());
        review.setRating(reviewDto.getRating());
        return review;
    }

    public ReviewDto toReviewDto(Review review) {
        if (review == null) {
            return null;
        }
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewId(review.getReviewId());
        reviewDto.setUser(new UserMapper().toUserDto(review.getUser()));
        reviewDto.setProduct(new ProductMapper().toProductDto(review.getProduct()));
        reviewDto.setReviewText(review.getReviewText());
        reviewDto.setRating(review.getRating());
        return reviewDto;
    }
}
