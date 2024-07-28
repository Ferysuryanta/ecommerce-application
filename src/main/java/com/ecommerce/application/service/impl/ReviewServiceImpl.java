package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.ReviewDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.handler.BusinessErrorCodes;
import com.ecommerce.application.mapper.ReviewMapper;
import com.ecommerce.application.repository.ReviewRepository;
import com.ecommerce.application.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    @Transactional
    public ReviewDto createReview(ReviewDto reviewDto) {
        var review = reviewMapper.toReview(reviewDto);
        var savedReview = reviewRepository.save(review);
        return reviewMapper.toReviewDto(savedReview);
    }

    @Override
    public Optional<ReviewDto> getReviewById(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .map(reviewMapper::toReviewDto);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toReviewDto)
                .toList();
    }

    @Override
    @Transactional
    public ReviewDto updateReview(UUID reviewId, ReviewDto reviewDto) {
        if (reviewRepository.existsById(reviewId)) {
            var review = reviewMapper.toReview(reviewDto);
            review.setReviewId(reviewId);
            var updateReview = reviewRepository.save(review);
            return reviewMapper.toReviewDto(updateReview);
        } else {
            throw new ResourceNotFoundException(BusinessErrorCodes.REVIEW_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteReview(UUID reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new ResourceNotFoundException(BusinessErrorCodes.REVIEW_NOT_FOUND);
        }
    }
}
